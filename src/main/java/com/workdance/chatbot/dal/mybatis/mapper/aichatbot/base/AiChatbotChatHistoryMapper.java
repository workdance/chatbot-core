package com.workdance.chatbot.dal.mybatis.mapper.aichatbot.base;

import com.workdance.chatbot.dal.mybatis.model.aichatbot.AiChatbotChatHistoryWithBrainDO;
import com.workdance.chatbot.dal.mybatis.model.aichatbot.base.AiChatbotChatHistoryDO;

import java.util.List;

public interface AiChatbotChatHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AiChatbotChatHistoryDO record);

    int insertSelective(AiChatbotChatHistoryDO record);

    AiChatbotChatHistoryDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AiChatbotChatHistoryDO record);

    int updateByPrimaryKey(AiChatbotChatHistoryDO record);

  int updateByMessageId(AiChatbotChatHistoryDO record);

    List<AiChatbotChatHistoryDO> selectAll();

    List<AiChatbotChatHistoryWithBrainDO> querySelectByChatId(String chatId);
}
