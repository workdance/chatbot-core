package com.workdance.chatbot.service.chat.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workdance.chatbot.dal.mybatisplus.dataobject.ChatDetailDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatBrainDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatDO;
import com.workdance.chatbot.dal.mybatisplus.mapper.AiChatbotBrainMapper;
import com.workdance.chatbot.dal.mybatisplus.mapper.AiChatbotChatBrainMapper;
import com.workdance.chatbot.dal.mybatisplus.mapper.AiChatbotChatMapper;
import com.workdance.chatbot.model.enums.ModelPlatformEnum;
import com.workdance.chatbot.service.chat.ChatBrainService;
import com.workdance.chatbot.service.chat.dto.ChatServiceReq;
import com.workdance.chatbot.service.llm.ModelService;
import com.workdance.chatbot.service.llm.dto.AnswerRep;
import com.workdance.chatbot.service.llm.dto.ConversationRep;
import com.workdance.chatbot.utils.StringUtil;
import com.workdance.chatbot.web.dto.inputs.ChatReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 聊天_智能体 服务实现类
 * </p>
 *
 * @author michael.sl
 * @since 2024-07-24
 */
@Service
public class ChatBrainServiceImpl extends ServiceImpl<AiChatbotChatBrainMapper, AiChatbotChatBrainDO> implements ChatBrainService {

    @Autowired(required = false)
    private AiChatbotBrainMapper aiChatbotBrainMapper;

    @Autowired(required = false)
    private AiChatbotChatMapper aiChatbotChatMapper;

    @Autowired
    @Qualifier("QianFanService")
    private ModelService qianfanService;

    @Override
    public List<AiChatbotBrainDO> getBrainListByChatId(String chatId) {

        List<AiChatbotBrainDO> list = aiChatbotBrainMapper.queryBrainByChatId(chatId);
        return list;
    }

    @Override
    @Transactional(transactionManager = "txManagerForSingle")
    public ChatDetailDO createChatAndBrain(ChatReq chatReq) {

        Assert.notNull(chatReq.getUserId(), "userId不能为空");
        Assert.notNull(chatReq.getChatName(), "chatName不能为空");
        Assert.notNull(chatReq.getBrainId(), "brainId不能为空");

        // 1.0 获取 brainId 对应的配置
        LambdaQueryWrapper<AiChatbotBrainDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AiChatbotBrainDO::getBrainId, chatReq.getBrainId());
        AiChatbotBrainDO aiChatbotBrainDO = aiChatbotBrainMapper.selectOne(queryWrapper);

        // 2.0 创建 chat
        AiChatbotChatDO chatDo = new AiChatbotChatDO();
        // 2.1 如果是 Brain 是百度文心一言，需要调用服务创建
        String chatId = AiChatbotChatDO.generateChatId();
        if (StringUtil.equals(aiChatbotBrainDO.getAppPlatform(), ModelPlatformEnum.BAIDU.getPlatform())) {
            ChatServiceReq chatServiceReq = new ChatServiceReq();
            chatServiceReq.setAppKey(aiChatbotBrainDO.getAppKey());
            chatServiceReq.setAppId(aiChatbotBrainDO.getAppId());
            ConversationRep conversationRep = qianfanService.createConversation(chatServiceReq);
            if(!StringUtil.isEmpty(conversationRep.getConversationId())) {
                chatId = conversationRep.getConversationId();
            } else {
                throw new IllegalArgumentException("创建百度文心一言会话失败");
            }
        }
        chatDo.setChatId(chatId);
        chatDo.setChatName(chatReq.getChatName());
        chatDo.setUserId(chatReq.getUserId());
        chatDo.setAvatar(aiChatbotBrainDO.getLogo());
        chatDo.setGmtCreate(new Date());
        chatDo.setGmtModified(new Date());

        int isOk = aiChatbotChatMapper.insert(chatDo);
        if (isOk > 0) {
            AiChatbotChatBrainDO chatBrain = new AiChatbotChatBrainDO();
            chatBrain.setChatId(chatId);
            chatBrain.setBrainId(chatReq.getBrainId());
            boolean isSaveOK = this.save(chatBrain);
            if (isSaveOK) {
                ChatDetailDO rst = aiChatbotChatMapper.selectByChatId(chatId);
                return rst;
            }
        }
        return null;
    }


    /**
     * 根据 Brain ID 进行聊天
     *
     * @param chatServiceReq 聊天服务请求对象，包含brain_id等参数
     * @return 返回一个Flux<String>对象，包含聊天产生的答案
     *
     * 本方法主要完成以下工作：
     * 1. 根据brain_id获取相应的配置信息
     * 2. 根据配置信息调用相应的模型服务来获取答案
     * 3. 返回相应的Flux<String>流
     */
    @Override
    public Flux<AnswerRep> chatByBrainId(ChatServiceReq chatServiceReq) {
        // 1.0 获取 brainId 对应的配置
        AiChatbotBrainDO aiChatbotBrainDO = aiChatbotBrainMapper.selectOne(new QueryWrapper<AiChatbotBrainDO>().eq("brain_id", chatServiceReq.getBrainId()));
        chatServiceReq.setAppId(aiChatbotBrainDO.getAppId());
        chatServiceReq.setAppKey(aiChatbotBrainDO.getAppKey());

        // 2.0 调用对应的模型服务获取相关的答案
        Flux<AnswerRep> answer = Flux.empty();

        if (Objects.equals(aiChatbotBrainDO.getAppPlatform(), ModelPlatformEnum.BAIDU.getPlatform())) {
            answer = qianfanService.askQuestion(chatServiceReq);
        }

        if (answer.equals(Flux.empty())) {
            throw new IllegalArgumentException("未找到对应的模型服务");
        }

        // 3.0 返回 Flux
        return answer;
    }

}
