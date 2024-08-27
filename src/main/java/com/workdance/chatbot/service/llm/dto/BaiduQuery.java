package com.workdance.chatbot.service.llm.dto;

import lombok.Data;

@Data
public class BaiduQuery {
    private String app_id;
    private Boolean stream;
    private String query;
    private String conversation_id;
}
