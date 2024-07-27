package com.workdance.chatbot.dal.mybatisplus.dataobject;

import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AiChatbotChatWithLastHistoryDO {
    private String chatId;
    private String chatName;
    private String avatar;
    private List<AiChatbotBrainDO> brains;
    private String userMessage;
    private Date messageGmtCreate;
    private Date chatGmtCreate;
}
