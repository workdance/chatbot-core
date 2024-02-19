package com.workdance.chatbot.service.chatHistory;

import com.workdance.chatbot.dal.mybatis.model.aichatbot.base.AiChatbotChatHistoryDO;

import java.util.List;

public interface ChatbotChatHistoryService{

    int deleteByPrimaryKey(Long id);

    int insert(AiChatbotChatHistoryDO record);

    int insertSelective(AiChatbotChatHistoryDO record);

    AiChatbotChatHistoryDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AiChatbotChatHistoryDO record);

    int updateByPrimaryKey(AiChatbotChatHistoryDO record);

    List<AiChatbotChatHistoryDO> selectAll();

}
