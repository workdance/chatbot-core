package com.workdance.chatbot.web.dto.inputs;

import com.workdance.chatbot.model.enums.BrainTypeEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BrainReq implements Serializable {
  /**
   * 大脑名称
   */
  private String name;

  /**
   * 大脑状态
   */
  private String status;

  private String description;

  private String model;

  private Long maxTokens;

  private Double temperature;

  /**
   * 提升模板
   */
  private String promptId;

  /**
   * 大脑类型
   */
  private String brainType;

  private String logo;

  @NotEmpty(message = "userId不能为空")
  private String userId;
}
