package com.workdance.chatbot.web.dto.inputs;

import lombok.Data;

@Data
public class ChatHistoryReq {
  public String question;
  public String assistant;
  public String chatId;
  public String brainId;
}
