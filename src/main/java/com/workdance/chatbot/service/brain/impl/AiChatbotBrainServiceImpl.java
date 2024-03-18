package com.workdance.chatbot.service.brain.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.workdance.chatbot.dal.mybatisplus.mapper.AiChatbotBrainMapper;
import com.workdance.chatbot.service.brain.IAiChatbotBrainService;
import org.springframework.stereotype.Service;

@Service
public class AiChatbotBrainServiceImpl extends ServiceImpl<AiChatbotBrainMapper, AiChatbotBrainDO> implements IAiChatbotBrainService {
  @Override
  public boolean updateBrainByBrainId(String brainId, AiChatbotBrainDO entity) {
    UpdateWrapper<AiChatbotBrainDO> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("brain_id", brainId);


    return this.update(entity, updateWrapper);
  }
}
