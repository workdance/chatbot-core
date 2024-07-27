package com.workdance.chatbot.web.dto.outputs;

import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import lombok.Data;

import java.util.List;

@Data
public class ChatDetailRep {
    private AiChatbotChatDO chat;
    private List<AiChatbotBrainDO> brains;
}
