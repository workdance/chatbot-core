package com.workdance.chatbot.dal.mybatisplus.mapper;

import com.workdance.chatbot.dal.mybatisplus.dataobject.AiChatbotChatWithLastHistoryDO;
import com.workdance.chatbot.dal.mybatisplus.dataobject.ChatDetailDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatDO;

import java.util.List;

public interface AiChatbotChatMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByChatId(String id);

    int insert(AiChatbotChatDO record);

    int insertSelective(AiChatbotChatDO record);

    AiChatbotChatDO selectByPrimaryKey(Long id);

    ChatDetailDO selectByChatId(String id);

    int updateByPrimaryKeySelective(AiChatbotChatDO record);

    int updateByChatIdSelective(AiChatbotChatDO record);

    int updateByPrimaryKey(AiChatbotChatDO record);

    List<AiChatbotChatDO> selectAll();

    List<AiChatbotChatDO> selectByUserId(String userId);

    List<AiChatbotChatWithLastHistoryDO> queryChatListWithHistoryByUserId(String userId);
}
