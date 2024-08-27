package com.workdance.chatbot.service.llm.dto;

import lombok.Data;

@Data
public class AnswerRep {
    private String conversationId;
    private String answer;
    private String messageId;
    private String requestId;
    private boolean isCompletion;

}
