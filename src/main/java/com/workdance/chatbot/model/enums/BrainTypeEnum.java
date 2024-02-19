package com.workdance.chatbot.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BrainTypeEnum {
  DOC("doc"),
  API("api");

  @EnumValue
  private final String value;
}
