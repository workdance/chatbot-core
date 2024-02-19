package com.workdance.chatbot.service.llm;

import com.workdance.chatbot.service.llm.dto.ChatServiceReq;
import com.workdance.chatbot.utils.listener.CompletedCallBack;
import reactor.core.publisher.Flux;
public interface ModelHttpService extends CompletedCallBack {
//    public InputStream handleStreamResponse() throws IOException;

    Flux<String> requestExternalService(ChatServiceReq chatToServiceVO);

    Flux<String> streamData();
}
