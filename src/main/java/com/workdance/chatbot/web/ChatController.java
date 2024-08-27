package com.workdance.chatbot.web;

import com.workdance.chatbot.dal.mybatisplus.dataobject.AiChatbotChatWithLastHistoryDO;
import com.workdance.chatbot.dal.mybatisplus.dataobject.ChatDetailDO;
import com.workdance.chatbot.dal.mybatisplus.entity.AiChatbotChatDO;
import com.workdance.chatbot.dal.mybatisplus.mapper.AiChatbotChatMapper;
import com.workdance.chatbot.service.chat.ChatBrainService;
import com.workdance.chatbot.service.chat.dto.ChatServiceReq;
import com.workdance.chatbot.service.llm.ModelService;
import com.workdance.chatbot.service.llm.dto.AnswerRep;
import com.workdance.chatbot.web.dto.inputs.ChatReq;
import com.workdance.chatbot.web.dto.outputs.ChatAnswer;
import com.workdance.chatbot.web.helper.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

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
    @Qualifier("OllamaService")
    private ModelService ollamaHttpService;

    @Autowired(required = false)
    private ChatBrainService chatBrainService;


    @GetMapping("/{id}")
    public Result<ChatDetailDO> detail(@PathVariable String id) {
        try {
            ChatDetailDO rst = chatDOMapper.selectByChatId(id);
            log.info("[chatController#detail] success=Y");
            return Result.success(rst);
        } catch (Exception e) {
            log.warn("系统异常", e);
            return Result.fail("查询失败");
        }
    }


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
    public Result<ChatDetailDO> add(@RequestBody ChatReq chatReq) {
        try {
            ChatDetailDO chatDo = chatBrainService.createChatAndBrain(chatReq);
            if (chatDo != null) {
                log.info("[chatController#add] success=Y chatId={}", chatDo.getChatId());
                return Result.success(chatDo);
            } else {
                return Result.fail("添加失败");
            }
        } catch (Exception e) {
            log.warn("系统异常", e);
            return Result.fail("添加失败");
        }
    }

    @DeleteMapping(value = "/{id}")
    public Result<String> delete(@PathVariable String id) {
        try {
            int isOk = chatDOMapper.deleteByChatId(id);
            if (isOk == 0) {
                log.info("[chatController#delete] success=N, status=bizError, chatId={}", id);
                return Result.success("delete fail");
            } else {
                log.info("[chatController#delete] success=Y");
                return Result.success("delete ok");
            }

        } catch (Exception e) {
            log.info("[chatController#delete] success=N, status=sysError, chatId={}", id);
            log.warn("系统异常", e);
            return Result.fail("对话删除失败");
        }
    }

    @PutMapping(value = "/{id}")
    public Result<AiChatbotChatDO> modify(@PathVariable String id, @RequestBody ChatReq chatReq) {
        AiChatbotChatDO chatDO = new AiChatbotChatDO();
        chatDO.setChatId(chatReq.getChatId());
        chatDO.setChatName(chatReq.getChatName());
        try {
            int isOk = chatDOMapper.updateByChatIdSelective(chatDO);
            if (isOk > 0) {
                log.info("[chatController#modify] success=Y chatId={}", id);
                return Result.success(chatDO);
            }
            return Result.fail("对话更新失败");
        } catch (Exception e) {
            log.warn("系统异常", e);
            log.info("[chatController#modify] success=N chatId={}", id);
            return Result.fail("对话更新系统异常");
        }
    }

    @GetMapping(value = "/{id}/question/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AnswerRep> chatWithQuestion(@PathVariable String id) throws IOException {
        ChatServiceReq chatToServiceVO = new ChatServiceReq();
//        chatToServiceVO.setQuestion(chatQuestionVO.getQuestion());
        chatToServiceVO.setQuestion("please tell me something about china");

        return ollamaHttpService.askQuestion(chatToServiceVO);
//        return ollamaHttpService.streamData();
//        SseEmitter emitter = new SseEmitter();
//        System.out.println("flux::::::");
//        System.out.println(streamFlux.collectList().block());d
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

    /**
     * 调用大模型服务完成对话
     * @param brainId BrainId
     * @param question 问题文本
     * @param conversationId 会话 Id，很多时候是 chatId
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/{brainId}/question", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatAnswer> chatWithQuestionV2(@PathVariable String brainId, @RequestParam String question, @RequestParam String conversationId) throws IOException {
        ChatServiceReq chatServiceReq = new ChatServiceReq();
        chatServiceReq.setBrainId(brainId);
        chatServiceReq.setConversationId(conversationId);
        chatServiceReq.setQuestion(question);
        chatServiceReq.setStream(true); // 流式服务
        Flux<ChatAnswer> chatAnswerFlux = Flux.empty();
        Flux<AnswerRep> stringFlux = chatBrainService.chatByBrainId(chatServiceReq);
        chatAnswerFlux = stringFlux.map(answerRep -> {
            ChatAnswer chatAnswer = new ChatAnswer();
            chatAnswer.setAnswer(answerRep.getAnswer());
            chatAnswer.setIsCompletion(answerRep.isCompletion());
            chatAnswer.setConversationId(answerRep.getConversationId());
            chatAnswer.setRequestId(answerRep.getRequestId());
            return chatAnswer;
        });
        log.info("[chatController#question] success=Y");
        return chatAnswerFlux;
    }
}


