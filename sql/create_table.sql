CREATE TABLE `ai_chatbot_chat`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `gmt_create`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    `gmt_modified` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    `deleted_at`   timestamp           NULL     DEFAULT NULL comment '删除时间',
    `chat_id`      varchar(100)                 DEFAULT NULL comment '聊天 id',
    `chat_name`    varchar(250)                 DEFAULT NULL comment '聊天名称',
    `user_id`      varchar(50)                  DEFAULT NULL comment '用户 Id',
    PRIMARY KEY (`id`)
) AUTO_INCREMENT = 100001
  DEFAULT CHARSET = utf8mb4 COMMENT = 'chatbot聊天';



CREATE TABLE `ai_chatbot_chat_history`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `gmt_create`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    `gmt_modified` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    `deleted_at`   timestamp           NULL     DEFAULT NULL comment '删除时间',
    `user_message` text                         DEFAULT NULL comment '用户提问',
    `assistant`    text                         DEFAULT NULL comment '机器回答',
    `chat_id`      varchar(50)                  DEFAULT NULL comment '聊天 ID',
    `message_id`   varchar(50)                  DEFAULT NULL comment '消息 ID',
    PRIMARY KEY (`id`)
) AUTO_INCREMENT = 100001
  DEFAULT CHARSET = utf8mb4 COMMENT = 'chatbot聊天记录';


CREATE TABLE `ai_chatbot_brain`
(
    `id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `name`      varchar(50) DEFAULT NULL comment '大脑名称',
    `status`      varchar(50) DEFAULT NULL comment '大脑状态',
    `description` TEXT,
    `model`       TEXT,
    `max_tokens`  bigint(20),
    `temperature` FLOAT,
    `prompt_id`   varchar(50) DEFAULT NULL comment '提升模板',
    `brain_type`  varchar(50) DEFAULT 'doc' comment '大脑类型',
    `gmt_create`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    `gmt_modified` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    `deleted_at`   timestamp           NULL     DEFAULT NULL comment '删除时间',
    PRIMARY KEY (`id`)
) AUTO_INCREMENT = 100001
  DEFAULT CHARSET = utf8mb4 COMMENT = 'chatbot大脑列表';



CREATE TABLE `ai_chatbot_prompt`
(
    `id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT comment '主键',
    `prompt_id`  varchar(50) DEFAULT NULL comment '业务 ID',
    `title`      varchar(50) DEFAULT NULL comment 'prompt名称',
    `content` TEXT DEFAULT NULL comment '内容',
    `status`      varchar(50) DEFAULT NULL comment '状态',
    `gmt_create`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    `gmt_modified` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    `deleted_at`   timestamp           NULL     DEFAULT NULL comment '删除时间',
    PRIMARY KEY (`id`)
) AUTO_INCREMENT = 100001
  DEFAULT CHARSET = utf8mb4 COMMENT = 'chatbot提示模板';