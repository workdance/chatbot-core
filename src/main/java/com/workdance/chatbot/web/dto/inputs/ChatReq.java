package com.workdance.chatbot.web.dto.inputs;

import lombok.Data;

@Data
public class ChatReq {
  public String chatName;
  public String workId;
  public String brainId;
  public String chatId;
  public String userId;
}
