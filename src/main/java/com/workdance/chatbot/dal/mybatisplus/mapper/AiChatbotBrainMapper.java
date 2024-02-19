package com.workdance.chatbot.dal.mybatisplus.mapper;

import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface AiChatbotBrainMapper extends BaseMapper<AiChatbotBrainDO> {
    int deleteByPrimaryKey(Long id);

    int insert(AiChatbotBrainDO record);

    int insertSelective(AiChatbotBrainDO record);

    AiChatbotBrainDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AiChatbotBrainDO record);

    int updateByPrimaryKey(AiChatbotBrainDO record);
}
