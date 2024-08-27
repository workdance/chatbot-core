package com.workdance.chatbot.web;

import com.workdance.chatbot.dal.mybatisplus.dataobject.AiChatbotChatHistoryWithBrainDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatHistoryDO;
import com.workdance.chatbot.dal.mybatisplus.mapper.AiChatbotChatHistoryMapper;
import com.workdance.chatbot.service.llm.ModelService;
import com.workdance.chatbot.web.dto.inputs.ChatHistoryReq;
import com.workdance.chatbot.web.helper.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Restful 示例
 */
@RestController
@RequestMapping("api/v1/chatHistory")
public class ChatHistoryController {
    private static final Logger log = LoggerFactory.getLogger(ChatHistoryController.class);

    @Autowired(required = false)
    private AiChatbotChatHistoryMapper chatHistoryMapper;

    @Autowired(required = false)
    @Qualifier("OllamaService")
    private ModelService ollamaHttpService;

    @PostMapping("/list")
    public Result<List<AiChatbotChatHistoryWithBrainDO>> list(@RequestBody ChatHistoryReq chatHistoryReq) {
        String chatId = chatHistoryReq.getChatId();
        List<AiChatbotChatHistoryWithBrainDO> list = null;
        try {
            list = chatHistoryMapper.querySelectByChatId(chatId);
            return Result.success(list);
        } catch (Exception e) {
          log.warn("系统异常", e);
          return Result.fail("对话历史失败");
        }
    }

  @PostMapping(value="/add")
  public Result<AiChatbotChatHistoryDO> add(@RequestBody ChatHistoryReq chatHistoryReq) {
    AiChatbotChatHistoryDO chatHistoryDO = new AiChatbotChatHistoryDO();
    chatHistoryDO.setChatId(chatHistoryReq.getChatId());
    chatHistoryDO.setAssistant(chatHistoryReq.getAssistant());
    chatHistoryDO.setMessageId(AiChatbotChatHistoryDO.generateMessageId());
    chatHistoryDO.setUserMessage(chatHistoryReq.getQuestion());
    chatHistoryDO.setBrainId(chatHistoryReq.getBrainId());
    chatHistoryDO.setGmtCreate(new Date());
    chatHistoryDO.setGmtModified(new Date());
    try {
      int id = chatHistoryMapper.insert(chatHistoryDO);
      chatHistoryDO.setId((long) id);
      return Result.success(chatHistoryDO);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("对话历史新增失败");
    }
  }

  @PutMapping(value="/{id}")
  public Result<AiChatbotChatHistoryDO> modify(@PathVariable String id, @RequestBody ChatHistoryReq chatHistoryReq) {
    AiChatbotChatHistoryDO chatHistoryDO = new AiChatbotChatHistoryDO();
    chatHistoryDO.setMessageId(id);
    chatHistoryDO.setAssistant(chatHistoryReq.getAssistant());
    try {
      chatHistoryMapper.updateByMessageId(chatHistoryDO);
      return Result.success(chatHistoryDO);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("对话历史更新失败");
    }
  }

  @DeleteMapping(value="/{id}")
  public Result<String> delete(@PathVariable String id) {
    try {
      chatHistoryMapper.deleteByPrimaryKey(Long.valueOf(id));
      return Result.success("delete ok");
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("对话历史更新失败");
    }
  }

}


