package com.workdance.chatbot.dal.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 聊天_智能体
 * </p>
 *
 * @author michael.sl
 * @since 2024-07-24
 */
@TableName("ai_chatbot_chat_brain")
public class AiChatbotChatBrainDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 聊天 id
     */
    private String chatId;

    /**
     * 智能体列表
     */
    private String brainId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getBrainId() {
        return brainId;
    }

    public void setBrainId(String brainId) {
        this.brainId = brainId;
    }

    @Override
    public String toString() {
        return "AiChatbotChatBrain{" +
        "id = " + id +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        ", deletedAt = " + deletedAt +
        ", chatId = " + chatId +
        ", brainId = " + brainId +
        "}";
    }
}
