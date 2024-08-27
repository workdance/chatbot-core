package com.workdance.chatbot.service.llm;

import com.workdance.chatbot.service.chat.dto.ChatServiceReq;
import com.workdance.chatbot.service.llm.dto.AnswerRep;
import com.workdance.chatbot.service.llm.dto.ConversationRep;
import com.workdance.chatbot.utils.listener.CompletedCallBack;
import reactor.core.publisher.Flux;


public interface ModelService extends CompletedCallBack {
//    public InputStream handleStreamResponse() throws IOException;

    // Flux<String> streamData();

    ConversationRep createConversation(ChatServiceReq chatServiceReq);

    Flux<AnswerRep> askQuestion(ChatServiceReq chatServiceReq);
}
