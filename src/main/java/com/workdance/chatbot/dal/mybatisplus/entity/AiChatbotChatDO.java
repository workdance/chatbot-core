package com.workdance.chatbot.dal.mybatisplus.entity;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * chatbot聊天DO
 */
@Data
public class AiChatbotChatDO {
    /**
    * 主键
    */
    private Long id;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 修改时间
    */
    private Date gmtModified;

    /**
    * 删除时间
    */
    private Date deletedAt;

    /**
    * 聊天 id
    */
    private String chatId;

    /**
    * 聊天名称
    */
    private String chatName;

    /**
    * 用户 Id
    */
    private String userId;

    /**
     * 头像
     */
    private String avatar;

    public static String generateChatId() {
        return  "chat-" + String.valueOf(UUID.randomUUID());
    }
}
