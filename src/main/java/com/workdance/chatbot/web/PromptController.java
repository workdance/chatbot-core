package com.workdance.chatbot.web;

import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotPromptDO;
import com.workdance.chatbot.service.prompt.IAiChatbotPromptService;
import com.workdance.chatbot.web.helper.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Restful 示例
 */
@RestController
@RequestMapping("api/v1/prompt")
public class PromptController {
  private static final Logger log = LoggerFactory.getLogger(PromptController.class);

  @Autowired(required = false)
  private IAiChatbotPromptService promptService;

  @PostMapping("/list")
  public Result<List<AiChatbotPromptDO>> List() {
    List<AiChatbotPromptDO> list = null;
    try {
      list = promptService.list();
      return Result.success(list);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("查询失败");
    }
  }

//  @PostMapping("/add")
//  public Result<AiChatbotChatDO> add(@RequestBody ChatReq chatReq) {
//    AiChatbotChatDO chatDo = new AiChatbotChatDO();
//    chatDo.setChatId(String.valueOf(UUID.randomUUID()));
//    chatDo.setChatName(chatReq.getChatName());
//    chatDo.setGmtCreate(new Date());
//    chatDo.setGmtModified(new Date());
//    chatDo.setUserId("105766");
//    try {
//      chatDOMapper.insert(chatDo);
//      return Result.success(chatDo);
//    } catch (Exception e) {
//      log.warn("系统异常", e);
//      return Result.fail("添加失败");
//    }
//  }
//
//  @DeleteMapping(value = "/{id}")
//  public Result<String> delete(@PathVariable String id) {
//    try {
//      chatDOMapper.deleteByPrimaryKey(Long.valueOf(id));
//      return Result.success("delete ok");
//    } catch (Exception e) {
//      log.warn("系统异常", e);
//      return Result.fail("对话删除失败");
//    }
//  }
//
//  @PutMapping(value = "/{id}")
//  public Result<AiChatbotChatDO> modify(@PathVariable String id, @RequestBody ChatReq chatReq) {
//    AiChatbotChatDO chatDO = new AiChatbotChatDO();
//    chatDO.setId(Long.valueOf(id));
//    chatDO.setChatName(chatReq.getChatName());
//    try {
//      chatDOMapper.updateByPrimaryKeySelective(chatDO);
//      return Result.success(chatDO);
//    } catch (Exception e) {
//      log.warn("系统异常", e);
//      return Result.fail("对话更新失败");
//    }
//  }
}


