package com.workdance.chatbot.dal.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.workdance.chatbot.model.enums.BrainTypeEnum;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * chatbot大脑列表
 * </p>
 *
 * @author michael.sl
 * @since 2024-02-17
 */
@TableName("ai_chatbot_brain")
public class AiChatbotBrainDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 大脑名称
     */
    private String name;

    /**
     * 大脑Logo
     */
    private String logo;
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
    private BrainTypeEnum brainType;

    /**
     * 创建时间
     */
    @TableField(value ="gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value ="gmt_modified", fill = FieldFill.UPDATE)
    private LocalDateTime gmtModified;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 大脑业务 Id
     */
    private String brainId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Long maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getPromptId() {
        return promptId;
    }

    public void setPromptId(String promptId) {
        this.promptId = promptId;
    }

    public BrainTypeEnum getBrainType() {
        return brainType;
    }

    public void setBrainType(BrainTypeEnum brainType) {
        this.brainType = brainType;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getBrainId() {
        return brainId;
    }

    public void setBrainId(String brainId) {
        this.brainId = brainId;
    }

    @Override
    public String toString() {
        return "AiChatbotBrain{" +
                "id = " + id +
                ", name = " + name +
                ", status = " + status +
                ", description = " + description +
                ", model = " + model +
                ", maxTokens = " + maxTokens +
                ", temperature = " + temperature +
                ", promptId = " + promptId +
                ", brainType = " + brainType +
                ", gmtCreate = " + gmtCreate +
                ", gmtModified = " + gmtModified +
                ", deletedAt = " + deletedAt +
                ", brainId = " + brainId +
                "}";
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public static String generateBrainId() {
        return  "brain-" + String.valueOf(UUID.randomUUID());
    }
}
