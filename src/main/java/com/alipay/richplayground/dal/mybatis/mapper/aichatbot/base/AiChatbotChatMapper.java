package com.alipay.richplayground.dal.mybatis.mapper.aichatbot;

import com.alipay.richplayground.dal.mybatis.model.aichatbot.AiChatbotChatDO;

import java.util.List;

public interface AiChatbotChatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AiChatbotChatDO record);

    int insertSelective(AiChatbotChatDO record);

    AiChatbotChatDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AiChatbotChatDO record);

    int updateByPrimaryKey(AiChatbotChatDO record);

    List<AiChatbotChatDO> selectAll();
}
