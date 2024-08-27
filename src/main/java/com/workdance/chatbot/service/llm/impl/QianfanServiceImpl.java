package com.workdance.chatbot.service.llm.impl;

import com.workdance.chatbot.service.chat.dto.ChatServiceReq;
import com.workdance.chatbot.service.llm.ModelService;
import com.workdance.chatbot.service.llm.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("QianFanService")
public class QianfanServiceImpl implements ModelService {
    private final Logger log = LoggerFactory.getLogger(QianfanServiceImpl.class);

    @Value("${qianfan_service_url}")
    private String serviceUrl;

    public ConversationRep createConversation(ChatServiceReq chatServiceReq) {
        String uri = this.serviceUrl;
        ConversationRep conversationRep = new ConversationRep();
        WebClient webclient = WebClient.builder().build();
        BaiduQuery jsonData = new BaiduQuery();
        jsonData.setApp_id(chatServiceReq.getAppId());
        Mono<BaiduRep> responseMono = webclient.post()
                .uri(uri)
                .headers(headers->{
                    headers.set("Content-Type", "application/json");
                    headers.set("X-Appbuilder-Authorization", "Bearer " + chatServiceReq.getAppKey());
                })
                .body(BodyInserters.fromObject(jsonData))
                .retrieve()
                .bodyToMono(BaiduRep.class);
        try {
            BaiduRep response = responseMono.block(); // 阻塞等待响应
            log.info("[responseMono]: " + response);
            conversationRep.setConversationId(response.getConversation_id());
        } catch (Exception e) {
            log.error("[responseMono error]: " + e.getMessage());
        }
        return conversationRep;
    }

    public Flux<AnswerRep> askQuestion(ChatServiceReq chatServiceReq) {
        String uri = this.serviceUrl + "/runs";
        WebClient webclient = WebClient.builder().build();
        BaiduQuery jsonData = new BaiduQuery();
        String token = chatServiceReq.getAppKey();
        // 1.0 鉴权数据
        jsonData.setApp_id(chatServiceReq.getAppId());
        jsonData.setQuery(chatServiceReq.getQuestion());
        // 1.1 业务信息
        jsonData.setStream(chatServiceReq.isStream());
        jsonData.setConversation_id(chatServiceReq.getConversationId());
        Flux<AnswerRep> openAiResponse = webclient.post()
                .uri(uri)
                .headers(headers->{
                    headers.set("Content-Type", "application/json");
                    headers.set("X-Appbuilder-Authorization", "Bearer " + token);
                })
                .accept(MediaType.TEXT_EVENT_STREAM) // 设置接受流媒体类型，这里是SSE
                .body(BodyInserters.fromObject(jsonData))
                .retrieve()
                .bodyToFlux(BaiduRep.class)
                .map(QianfanServiceImpl::convertToAnswerRep)
                .onErrorResume(WebClientResponseException.class, ex -> {
                  HttpStatus status = (HttpStatus) ex.getStatusCode();
                  String res = ex.getResponseBodyAsString();
                  log.error("Baidu API error: {} {}", status, res);
                  return Mono.error(new RuntimeException(res));
                });

      // 订阅Flux流并处理每个接收到的数据项
      openAiResponse.subscribe(
              data -> {
                  // System.out.println("Received data: " + data)
              },
              error -> log.error("Error occurred: " + error),
              () -> log.info("Streaming finished.")
      );
      return openAiResponse;
    }

  @Override
  public void completed(Message questions, String sessionId, String response) {
    log.info("请求成功 sessionId:{}", sessionId);
  }

  @Override
  public void fail(Message questions, String sessionId, String response) {
    log.error("处理失败 sessionId:{},questions:{},errorMsg:{}", sessionId, questions, response);
  }

  @Override
  public void clearHistory(String sessionId) {
    log.info("清除历史记录 sessionId:{}", sessionId);
  }

  public static AnswerRep convertToAnswerRep(BaiduRep baiduRep) {
        if (baiduRep==null) {
            return null;
        }
        AnswerRep answerRep = new AnswerRep();
        answerRep.setAnswer(baiduRep.getAnswer());
        answerRep.setCompletion(baiduRep.is_completion());
        answerRep.setConversationId(baiduRep.getConversation_id());
        answerRep.setRequestId(baiduRep.getRequest_id());
        answerRep.setMessageId(baiduRep.getMessage_id());
        return answerRep;
  }
}
