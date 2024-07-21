package com.workdance.chatbot.web;

import com.workdance.chatbot.dal.mybatis.mapper.aichatbot.base.AiChatbotChatMapper;
import com.workdance.chatbot.dal.mybatis.model.aichatbot.AiChatbotChatWithLastHistoryDO;
import com.workdance.chatbot.dal.mybatis.model.aichatbot.base.AiChatbotChatDO;
import com.workdance.chatbot.service.llm.ModelHttpService;
import com.workdance.chatbot.service.llm.dto.ChatServiceReq;
import com.workdance.chatbot.web.dto.inputs.ChatReq;
import com.workdance.chatbot.web.helper.Result;
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
@RequestMapping("api/v1/chat")
public class ChatController {
  private static final Logger log = LoggerFactory.getLogger(ChatController.class);

  @Autowired(required = false)
  private AiChatbotChatMapper chatDOMapper;

  @Autowired(required = false)
  private ModelHttpService ollamaHttpService;

  @PostMapping("/list")
  public Result<List<AiChatbotChatDO>> List(@RequestBody ChatReq chatReq) {
    String workId = chatReq.getWorkId();
    List<AiChatbotChatDO> list = null;
    try {
      list = chatDOMapper.selectByUserId(workId);
      return Result.success(list);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("查询失败");
    }
  }

  @PostMapping("/listChat")
  public Result<List<AiChatbotChatWithLastHistoryDO>> ListChat(@RequestBody ChatReq chatReq) {
    String workId = chatReq.getWorkId();
    List<AiChatbotChatWithLastHistoryDO> list = null;
    try {
      list = chatDOMapper.queryChatListWithHistoryByUserId(workId);
      return Result.success(list);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("查询失败");
    }
  }

  @PostMapping("/add")
  public Result<AiChatbotChatDO> add(@RequestBody ChatReq chatReq) {
    AiChatbotChatDO chatDo = new AiChatbotChatDO();
    chatDo.setChatId(String.valueOf(UUID.randomUUID()));
    chatDo.setChatName(chatReq.getChatName());
    chatDo.setUserId(chatReq.getWorkId());
    chatDo.setGmtCreate(new Date());
    chatDo.setGmtModified(new Date());
    try {
      chatDOMapper.insert(chatDo);
      return Result.success(chatDo);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("添加失败");
    }
  }

  @DeleteMapping(value = "/{id}")
  public Result<String> delete(@PathVariable String id) {
    try {
      chatDOMapper.deleteByPrimaryKey(Long.valueOf(id));
      return Result.success("delete ok");
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("对话删除失败");
    }
  }

  @PutMapping(value = "/{id}")
  public Result<AiChatbotChatDO> modify(@PathVariable String id, @RequestBody ChatReq chatReq) {
    AiChatbotChatDO chatDO = new AiChatbotChatDO();
    chatDO.setId(Long.valueOf(id));
    chatDO.setChatName(chatReq.getChatName());
    try {
      chatDOMapper.updateByPrimaryKeySelective(chatDO);
      return Result.success(chatDO);
    } catch (Exception e) {
      log.warn("系统异常", e);
      return Result.fail("对话更新失败");
    }
  }

  @GetMapping(value = "/{id}/question/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<String> chatWithQuestion(@PathVariable String id) throws IOException {
    ChatServiceReq chatToServiceVO = new ChatServiceReq();
//        chatToServiceVO.setQuestion(chatQuestionVO.getQuestion());
    chatToServiceVO.setQuestion("please tell me something about china");

    return ollamaHttpService.requestExternalService(chatToServiceVO);
//        return ollamaHttpService.streamData();
//        SseEmitter emitter = new SseEmitter();
//        System.out.println("flux::::::");
//        System.out.println(streamFlux.collectList().block());
//        streamFlux.subscribe(
//                // 数据处理
//                data -> {
//                    try {
//                        emitter.send(data);
//                        System.out.println(data);
//                    } catch (IOException e) {
//                        emitter.completeWithError(e);
//                    }
//                },
//                // 错误处理
//                emitter::completeWithError,
//                // 完成处理
//                emitter::complete
//        );
//
//        return emitter;
  }
}


