package com.workdance.chatbot.utils.listener;


import com.workdance.chatbot.service.llm.dto.Message;

public interface CompletedCallBack {

  /**
   * 成功完成回调用
   *
   * @param questions
   * @param sessionId
   * @param response
   */
  void completed(Message questions, String sessionId, String response);

  /**
   * 失败回调
   *
   * @param questions
   * @param sessionId
   * @param response
   */
  void fail(Message questions, String sessionId, String response);

  /**
   * 清除历史
   * @param sessionId
   */
  void clearHistory(String sessionId);
}
