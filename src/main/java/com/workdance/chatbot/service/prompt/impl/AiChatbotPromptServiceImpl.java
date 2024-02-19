package com.workdance.chatbot.service.prompt.impl;

import com.workdance.chatbot.dal.mybatisplus.mapper.AiChatbotPromptMapper;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotPromptDO;
import com.workdance.chatbot.service.prompt.IAiChatbotPromptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * chatbot提示模板 服务实现类
 * </p>
 *
 * @author michael.sl
 * @since 2024-02-17
 */
@Service
public class AiChatbotPromptServiceImpl extends ServiceImpl<AiChatbotPromptMapper, AiChatbotPromptDO> implements IAiChatbotPromptService {

}
