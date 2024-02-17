package com.alipay.richplayground.dal.mybatis.mapper.aichatbot;

import com.alipay.richplayground.dal.mybatis.model.aichatbot.AiChatbotChatHistoryDO;

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

    List<AiChatbotChatHistoryDO> querySelectByChatId(String chatId);
}
