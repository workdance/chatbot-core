package com.workdance.chatbot.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BrainStatusEnum {
  PRIVATE("private"),
  PUBLIC("public");

  @EnumValue
  private final String value;
}
