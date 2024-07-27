package com.workdance.chatbot.dal.mybatisplus.dataobject;

import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatHistoryDO;
import lombok.Data;

@Data
public class AiChatbotChatHistoryWithBrainDO extends AiChatbotChatHistoryDO {
    private String brainName;

    private String brainLogo;

    private String brainId;
}
