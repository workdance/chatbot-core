package com.workdance.chatbot.service.brain;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * chatbot大脑列表 服务类
 * </p>
 *
 * @author michael.sl
 * @since 2024-02-17
 */
public interface IAiChatbotBrainService extends IService<AiChatbotBrainDO> {

  boolean updateBrainByBrainId(String brainId, AiChatbotBrainDO entity);
}
