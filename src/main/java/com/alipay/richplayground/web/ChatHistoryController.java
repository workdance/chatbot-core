package com.alipay.richplayground.web;

import com.alipay.richplayground.dal.mybatis.mapper.aichatbot.AiChatbotChatHistoryMapper;
import com.alipay.richplayground.dal.mybatis.mapper.aichatbot.AiChatbotChatMapper;
import com.alipay.richplayground.dal.mybatis.model.aichatbot.AiChatbotChatDO;
import com.alipay.richplayground.dal.mybatis.model.aichatbot.AiChatbotChatHistoryDO;
import com.alipay.richplayground.model.ChatVO;
import com.alipay.richplayground.service.llm.ModelHttpService;
import com.alipay.richplayground.service.llm.dto.ChatServiceReq;
import com.alipay.richplayground.web.dto.ChatHistoryReq;
import com.alipay.richplayground.web.helper.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private ModelHttpService ollamaHttpService;

    @PostMapping("/list")
    public Result<List<AiChatbotChatHistoryDO>> list(@RequestBody ChatHistoryReq chatHistoryReq) {
        String chatId = chatHistoryReq.getChatId();
        List<AiChatbotChatHistoryDO> list = null;
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
    chatHistoryDO.setMessageId(String.valueOf(UUID.randomUUID()));
    chatHistoryDO.setUserMessage(chatHistoryReq.getQuestion());
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


