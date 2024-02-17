package com.alipay.richplayground.service.chatHistory;

import com.alipay.richplayground.dal.mybatis.model.aichatbot.AiChatbotChatHistoryDO;

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
