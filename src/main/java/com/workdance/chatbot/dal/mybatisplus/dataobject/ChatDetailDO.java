package com.workdance.chatbot.dal.mybatisplus.dataobject;

import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChatDetailDO extends AiChatbotChatDO {
    public List<AiChatbotBrainDO> brains;
}
