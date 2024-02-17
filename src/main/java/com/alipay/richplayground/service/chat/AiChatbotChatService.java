package com.alipay.richplayground.service.chat;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.richplayground.dal.mybatis.mapper.aichatbot.AiChatbotChatMapper;
import com.alipay.richplayground.dal.mybatis.model.aichatbot.AiChatbotChatDO;

import java.util.List;

@Service
public class AiChatbotChatService{

    @Autowired
    private AiChatbotChatMapper aiChatbotChatMapper;


    public int deleteByPrimaryKey(Long id) {
        return aiChatbotChatMapper.deleteByPrimaryKey(id);
    }


    public int insert(AiChatbotChatDO record) {
        return aiChatbotChatMapper.insert(record);
    }


    public int insertSelective(AiChatbotChatDO record) {
        return aiChatbotChatMapper.insertSelective(record);
    }


    public AiChatbotChatDO selectByPrimaryKey(Long id) {
        return aiChatbotChatMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(AiChatbotChatDO record) {
        return aiChatbotChatMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(AiChatbotChatDO record) {
        return aiChatbotChatMapper.updateByPrimaryKey(record);
    }

    public List<AiChatbotChatDO> selectAll() {
        return aiChatbotChatMapper.selectAll();
    }
}
