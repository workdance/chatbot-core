package com.workdance.chatbot.dal.mybatis.mapper.aichatbot.base;

import com.workdance.chatbot.dal.mybatis.model.aichatbot.base.AiChatbotChatDO;

import java.util.List;

public interface AiChatbotChatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AiChatbotChatDO record);

    int insertSelective(AiChatbotChatDO record);

    AiChatbotChatDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AiChatbotChatDO record);

    int updateByPrimaryKey(AiChatbotChatDO record);

    List<AiChatbotChatDO> selectAll();

    List<AiChatbotChatDO> selectByUserId(String userId);
}
