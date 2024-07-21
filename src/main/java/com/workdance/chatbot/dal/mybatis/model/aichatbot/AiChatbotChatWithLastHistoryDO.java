package com.workdance.chatbot.dal.mybatis.model.aichatbot;

import com.workdance.chatbot.dal.mybatis.model.aichatbot.base.AiChatbotChatDO;
import lombok.Data;

import java.util.Date;

@Data
public class AiChatbotChatWithLastHistoryDO {
    private String chatId;
    private String chatName;
    private String avatar;
    private String userMessage;
    private Date messageGmtCreate;
    private Date chatGmtCreate;
}
