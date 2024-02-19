package com.workdance.chatbot.dal.mybatisplus.entity;

import com.workdance.chatbot.model.enums.BrainStatusEnum;
import com.workdance.chatbot.model.enums.BrainTypeEnum;
import lombok.Data;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * chatbot大脑列表
 */
@Data
public class AiChatbotBrainDO {
    /**
    * 主键
    */
    @NotNull(message = "主键is not null")
    private Long id;

    /**
    * 大脑名称
    */
    @Size(max = 50,message = "大脑名称max length should less than 50")
    private String name;

    /**
    * 大脑状态
    */
    @Size(max = 50,message = "大脑状态max length should less than 50")
    private BrainStatusEnum status;

    private String description;

    private String model;

    private Long maxTokens;

    private Double temperature;

    /**
    * 提升模板
    */
    @Size(max = 50,message = "提升模板max length should less than 50")
    private String promptId;

    /**
    * 大脑类型
    */
    @Size(max = 50,message = "大脑类型max length should less than 50")
    private BrainTypeEnum brainType;

    /**
    * 创建时间
    */
    @NotNull(message = "创建时间is not null")
    private Date gmtCreate;

    /**
    * 修改时间
    */
    @NotNull(message = "修改时间is not null")
    private Date gmtModified;

    /**
    * 删除时间
    */
    private Date deletedAt;

    /**
    * 大脑业务 Id
    */
    @Size(max = 50,message = "大脑业务 Idmax length should less than 50")
    private String brainId;
}
