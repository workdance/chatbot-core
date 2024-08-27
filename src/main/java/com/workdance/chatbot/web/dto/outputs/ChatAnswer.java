package com.workdance.chatbot.web.dto.outputs;

import lombok.Data;

@Data
public class ChatAnswer {
    private String eventType;
    private String answer;
    private String conversationId;
    private String requestId;
    private Boolean isCompletion;
}
