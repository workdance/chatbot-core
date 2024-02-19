package com.workdance.chatbot.service.chatHistory.impl;

import com.workdance.chatbot.dal.mybatis.mapper.aichatbot.base.AiChatbotChatHistoryMapper;
import com.workdance.chatbot.dal.mybatis.model.aichatbot.base.AiChatbotChatHistoryDO;
import com.workdance.chatbot.service.chatHistory.ChatbotChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatbotChatHistoryServiceImpl implements ChatbotChatHistoryService{

    @Autowired
    private AiChatbotChatHistoryMapper chatbotChatHistoryMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return chatbotChatHistoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AiChatbotChatHistoryDO record) {
        return chatbotChatHistoryMapper.insert(record);
    }

    @Override
    public int insertSelective(AiChatbotChatHistoryDO record) {
        return chatbotChatHistoryMapper.insertSelective(record);
    }

    @Override
    public AiChatbotChatHistoryDO selectByPrimaryKey(Long id) {
        return chatbotChatHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AiChatbotChatHistoryDO record) {
        return chatbotChatHistoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AiChatbotChatHistoryDO record) {
        return chatbotChatHistoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<AiChatbotChatHistoryDO> selectAll() {
        return chatbotChatHistoryMapper.selectAll();
    }

}
