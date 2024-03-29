package com.workdance.chatbot.service.llm.dto;

import com.workdance.chatbot.utils.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
  private MessageType messageType;
  private String message;
  private Date date;

  public Message(MessageType messageType, String message) {
    this.messageType = messageType;
    this.message = message;
    this.date = new Date();
  }
}
