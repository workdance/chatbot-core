package com.workdance.chatbot.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotBrainDO;
import com.workdance.chatbot.service.brain.IAiChatbotBrainService;
import com.workdance.chatbot.service.llm.ModelHttpService;
import com.workdance.chatbot.web.dto.inputs.BrainReq;
import com.workdance.chatbot.web.helper.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
  public Result<List<AiChatbotBrainDO>> List(@RequestBody BrainReq brainReq) {
    List<AiChatbotBrainDO> list = null;
    try {
      LambdaQueryWrapper<AiChatbotBrainDO> queryWrapper = new LambdaQueryWrapper<>();
      queryWrapper.like(AiChatbotBrainDO::getUserId, brainReq.getUserId());
      list = brainService.list(queryWrapper);
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
    entityDO.setDescription(brainReq.getDescription());
    entityDO.setBrainId(String.valueOf(UUID.randomUUID()));
    entityDO.setBrainType(brainReq.getBrainType());
    try {
      brainService.save(entityDO);
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
      brainService.removeById(Long.valueOf(id));
      return Result.success("delete ok");
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("大脑删除失败");
    }
  }

  @PutMapping(value = "/{id}")
  public Result<AiChatbotBrainDO> modify(@PathVariable String id, @RequestBody BrainReq brainReq) {
    AiChatbotBrainDO entity = new AiChatbotBrainDO();
    entity.setBrainId(id);
    entity.setModel(brainReq.getModel());
    entity.setName(brainReq.getName());
    try {
      boolean updateOk = brainService.updateBrainByBrainId(id, entity);
      if (updateOk) {
        return Result.success(entity);
      } else {
        return Result.fail("大脑更新失败");
      }
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("大脑更新失败");
    }
  }
}


