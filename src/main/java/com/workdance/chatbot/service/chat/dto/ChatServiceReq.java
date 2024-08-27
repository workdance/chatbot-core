package com.workdance.chatbot.service.chat.dto;

import lombok.Data;

@Data()
public class ChatServiceReq {
    private String brainId;
    private String conversationId;
    private String question;
    private String query;
    private String appId;
    private String appKey;
    private boolean isStream;
}
