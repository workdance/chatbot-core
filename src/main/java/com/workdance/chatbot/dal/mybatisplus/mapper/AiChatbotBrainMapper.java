package com.workdance.chatbot.dal.mybatisplus.mapper;

import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface AiChatbotBrainMapper extends BaseMapper<AiChatbotBrainDO> {
    List<AiChatbotBrainDO> queryBrainByChatId(String chatId);
}
