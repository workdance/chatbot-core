package com.alipay.richplayground.service.llm.impl;

import com.alipay.richplayground.service.llm.ModelHttpService;
import com.alipay.richplayground.service.llm.dto.ChatServiceReq;
import com.alipay.richplayground.service.llm.dto.Message;
import com.alipay.richplayground.utils.enums.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
public class OllamaHttpServiceImpl implements ModelHttpService {
//    private final CloseableHttpClient httpClient;

    @Value("${ollama_service_url}")
    private String serviceUrl;


//    @Autowired
//    public OllamaHttpService(CloseableHttpClient httpClient) {
//        this.httpClient = httpClient;
//    }

    public Flux<String> requestExternalService(ChatServiceReq chatServiceReq) {
        String uri = this.serviceUrl + "?question=" + chatServiceReq.getQuestion();
        WebClient webclient = WebClient.builder().build();
        Flux<String> openAiResponse = webclient.get()
                .uri(uri)
                .accept(MediaType.TEXT_EVENT_STREAM) // 设置接受流媒体类型，这里是SSE
                .retrieve()
                .bodyToFlux(String.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                  HttpStatus status = (HttpStatus) ex.getStatusCode();
                  String res = ex.getResponseBodyAsString();
                  log.error("OpenAI API error: {} {}", status, res);
                  return Mono.error(new RuntimeException(res));
                });
      Message userMessage = new Message(MessageType.TEXT, chatServiceReq.getQuestion());

      // 订阅Flux流并处理每个接收到的数据项
      openAiResponse.subscribe(
              data -> System.out.println("Received data: " + data),
              error -> System.err.println("Error occurred: " + error),
              () -> System.out.println("Streaming finished.")
      );
      return openAiResponse;
//        Flux<String> streamFlux = ollamaHttpService.streamData();
//      return Flux.create(emitter -> {
//        System.out.println(emitter);
//        ChatMessageSubscriber subscriber = new ChatMessageSubscriber(emitter, "abc", this, userMessage);
//        openAiResponse.subscribe(subscriber);
//        emitter.onDispose(subscriber);
//      });
    }

    public Flux<String> streamData() {
        // 这只是一个简单的示例，它生成每秒一个字符串的无限流
        return Flux
                .interval(Duration.ofSeconds(1))
                .map(l -> "Data " + l);
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
}
