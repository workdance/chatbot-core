package com.workdance.chatbot.web.dto.inputs;

import com.workdance.chatbot.model.enums.BrainTypeEnum;
import lombok.Data;

@Data
public class KnowledgeReq {
  /**
   * 大脑名称
   */
  private String name;

  /**
   * 文件地址
   */
  private String url;
  /**
   * 大脑 Id
   */
  private String brainId;
}
