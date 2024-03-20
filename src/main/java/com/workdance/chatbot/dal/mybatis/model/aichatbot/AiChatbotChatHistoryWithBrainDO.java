package com.workdance.chatbot.dal.mybatis.model.aichatbot;

import com.workdance.chatbot.dal.mybatis.model.aichatbot.base.AiChatbotChatHistoryDO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AiChatbotChatHistoryWithBrainDO extends AiChatbotChatHistoryDO {
    private String brainName;

    private String brainLogo;

    private String brainId;
}
