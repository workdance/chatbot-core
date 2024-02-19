package com.workdance.chatbot.web;

import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.workdance.chatbot.service.brain.IAiChatbotBrainService;
import com.workdance.chatbot.service.llm.ModelHttpService;
import com.workdance.chatbot.web.dto.BrainReq;
import com.workdance.chatbot.web.dto.ChatReq;
import com.workdance.chatbot.web.helper.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Restful 示例
 */
@RestController
@RequestMapping("api/v1/brain")
public class BrainController {
  private static final Logger log = LoggerFactory.getLogger(BrainController.class);

  @Autowired(required = false)
  private IAiChatbotBrainService brainService;

  @Autowired(required = false)
  private ModelHttpService ollamaHttpService;

  @PostMapping("/list")
  public Result<List<AiChatbotBrainDO>> List() {
    List<AiChatbotBrainDO> list = null;
    try {
      list = brainService.list();
      return Result.success(list);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("查询失败");
    }
  }

  @PostMapping("/add")
  public Result<AiChatbotBrainDO> add(@RequestBody BrainReq brainReq) {
    AiChatbotBrainDO entityDO = new AiChatbotBrainDO();
    entityDO.setName(brainReq.getName());
    entityDO.setBrainId(String.valueOf(UUID.randomUUID()));
    entityDO.setBrainType(brainReq.getBrainType());
    entityDO.setGmtCreate(new Date());
    entityDO.setGmtModified(new Date());
    try {
      brainService.save(entityDO);
      return Result.success(entityDO);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("添加失败");
    }
  }

  @DeleteMapping(value = "/{id}")
  public Result<String> delete(@PathVariable String id) {
    try {
      brainService.removeById(Long.valueOf(id));
      return Result.success("delete ok");
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("对话删除失败");
    }
  }

  @PutMapping(value = "/{id}")
  public Result<AiChatbotBrainDO> modify(@PathVariable String id, @RequestBody ChatReq chatReq) {
    AiChatbotBrainDO entity = new AiChatbotBrainDO();
    entity.setId(Long.valueOf(id));
    entity.setName(chatReq.getChatName());
    try {
      brainService.updateByPrimaryKeySelective(entity);
      return Result.success(entity);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("对话更新失败");
    }
  }
}


