package com.alipay.richplayground.service.llm;

import com.alipay.richplayground.service.llm.dto.ChatServiceReq;
import com.alipay.richplayground.utils.listener.CompletedCallBack;
import reactor.core.publisher.Flux;
public interface ModelHttpService extends CompletedCallBack {
//    public InputStream handleStreamResponse() throws IOException;

    Flux<String> requestExternalService(ChatServiceReq chatToServiceVO);

    Flux<String> streamData();
}
