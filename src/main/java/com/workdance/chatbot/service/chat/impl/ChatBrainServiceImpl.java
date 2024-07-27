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
import com.workdance.chatbot.service.chat.ChatBrainService;
import com.workdance.chatbot.web.dto.inputs.ChatReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

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

        LambdaQueryWrapper<AiChatbotBrainDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AiChatbotBrainDO::getBrainId, chatReq.getBrainId());

        AiChatbotBrainDO aiChatbotBrainDO = aiChatbotBrainMapper.selectOne(queryWrapper);

        AiChatbotChatDO chatDo = new AiChatbotChatDO();
        String chatId = AiChatbotChatDO.generateChatId();
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
}
