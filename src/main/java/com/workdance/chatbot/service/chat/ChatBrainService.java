package com.workdance.chatbot.service.chat;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workdance.chatbot.dal.mybatisplus.dataobject.ChatDetailDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatBrainDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatDO;
import com.workdance.chatbot.web.dto.inputs.ChatReq;

import java.util.List;

/**
 * <p>
 * 聊天_智能体 服务类
 * </p>
 *
 * @author michael.sl
 * @since 2024-07-24
 */
public interface ChatBrainService extends IService<AiChatbotChatBrainDO> {
    public List<AiChatbotBrainDO> getBrainListByChatId(String chatId);

    public ChatDetailDO createChatAndBrain(ChatReq chatReq);
}
