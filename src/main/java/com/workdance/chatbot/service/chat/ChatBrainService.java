package com.workdance.chatbot.service.chat;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workdance.chatbot.dal.mybatisplus.dataobject.ChatDetailDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatBrainDO;
import com.workdance.chatbot.service.chat.dto.ChatServiceReq;
import com.workdance.chatbot.service.llm.dto.AnswerRep;
import com.workdance.chatbot.web.dto.inputs.ChatReq;
import reactor.core.publisher.Flux;

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
    List<AiChatbotBrainDO> getBrainListByChatId(String chatId);

    ChatDetailDO createChatAndBrain(ChatReq chatReq);

    Flux<AnswerRep> chatByBrainId(ChatServiceReq chatServiceReq);
}
