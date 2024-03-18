package com.workdance.chatbot.dal.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * chatbot知识库
 * </p>
 *
 * @author michael.sl
 * @since 2024-03-14
 */
@TableName("ai_chatbot_knowledge")
public class AiChatbotKnowledgeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 大脑名称
     */
    private String brainId;

    /**
     * 知识库名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrainId() {
        return brainId;
    }

    public void setBrainId(String brainId) {
        this.brainId = brainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public String toString() {
        return "AiChatbotKnowledge{" +
        "id = " + id +
        ", brainId = " + brainId +
        ", name = " + name +
        ", url = " + url +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        ", deletedAt = " + deletedAt +
        "}";
    }
}
