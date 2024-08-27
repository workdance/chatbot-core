package com.workdance.chatbot.service.llm.dto;

import lombok.Data;

@Data
public class BaiduRep {
    private String request_id;
    private String conversation_id;
    private String answer;
    private String message_id;
    private boolean is_completion;

}
