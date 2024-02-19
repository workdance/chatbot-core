package com.workdance.chatbot.web.dto;

import lombok.Data;

@Data
public class ChatHistoryReq {
  public String question;
  public String assistant;
  public String chatId;
}
