package com.workdance.chatbot.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotKnowledgeDO;
import com.workdance.chatbot.service.brain.IAiChatbotBrainService;
import com.workdance.chatbot.service.knowledge.IAiChatbotKnowledgeService;
import com.workdance.chatbot.web.dto.inputs.KnowledgeReq;
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
@RequestMapping("api/v1/knowledge")
public class KnowledgeController {
  private static final Logger log = LoggerFactory.getLogger(KnowledgeController.class);

  @Autowired(required = false)
  private IAiChatbotBrainService brainService;

  @Autowired(required = false)
  private IAiChatbotKnowledgeService knowledgeService;

  @PostMapping("/list")
  public Result<List<AiChatbotKnowledgeDO>> List() {
    List<AiChatbotKnowledgeDO> list = null;
    try {
      list = knowledgeService.list();
      return Result.success(list);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("查询失败");
    }
  }

  @PostMapping("/add")
  public Result<AiChatbotKnowledgeDO> add(@RequestBody KnowledgeReq knowledgeReq) {
    AiChatbotKnowledgeDO entityDO = new AiChatbotKnowledgeDO();
    entityDO.setName(knowledgeReq.getName());
    entityDO.setBrainId(knowledgeReq.getBrainId());
    entityDO.setUrl(knowledgeReq.getUrl());
    try {
      knowledgeService.save(entityDO);
      return Result.success(entityDO);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("添加失败");
    }
  }

  @GetMapping(value = "/{id}")
  public Result<AiChatbotBrainDO> detail(@PathVariable String id) {
    try {
      LambdaQueryWrapper<AiChatbotBrainDO> queryWrapper = new LambdaQueryWrapper<>();
      queryWrapper.like(AiChatbotBrainDO::getBrainId, id);
      AiChatbotBrainDO entityDo = brainService.getOne(queryWrapper);
      return Result.success(entityDo);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("查询失败");
    }
  }

  @DeleteMapping(value = "/{id}")
  public Result<String> delete(@PathVariable String id) {
    try {
      knowledgeService.removeById(Long.valueOf(id));
      return Result.success("delete ok");
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("对话删除失败");
    }
  }

}


