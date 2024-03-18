package com.workdance.chatbot.dal.mybatis.model.aichatbot;

import com.workdance.chatbot.dal.mybatis.model.aichatbot.base.AiChatbotChatHistoryDO;

public class AiChatbotChatHistoryWithBrainDO extends AiChatbotChatHistoryDO {
    private String brainName;

    private String brainLogo;

    public String getBrainName() {
        return brainName;
    }

    public void setBrainName(String brainName) {
        this.brainName = brainName;
    }

    public String getBrainLogo() {
        return brainLogo;
    }

    public void setBrainLogo(String brainLogo) {
        this.brainLogo = brainLogo;
    }
}
