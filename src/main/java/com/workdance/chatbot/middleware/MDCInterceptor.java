package com.workdance.chatbot.middleware;
import com.workdance.chatbot.utils.StringUtil;
import com.workdance.chatbot.utils.TraceIdUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MDCInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader("X-Trace-Id"); // 假设 traceId 来自请求头
        if (StringUtil.isEmpty(traceId)) {
            TraceIdUtil.setTraceId(TraceIdUtil.generateTraceId()); // 生成一个新的 traceId
        } else {
            TraceIdUtil.setTraceId(traceId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //调用结束后删除
        TraceIdUtil.remove();
    }
}
