/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.richplayground.web.helper;

import com.alipay.richplayground.utils.enums.ErrorCodeEnum;


/**
 * web层专用result
 */
public class Result<T> {
    /**
     * 当前的TraceId
     */
    private String  traceId;
    /**
     * 返回数据
     */
    private T       data;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 错误码
     */
    private String  errorCode;
    /**
     * 错误消息
     */
    private String  errorMessage;

    public static <T> Result<T> success(T val) {
        Result<T> result = new Result<T>();
        result.data = val;
        result.success = true;
        return result;
    }

    public static <T> Result<T> fail(String errorMessage) {
        Result<T> result = new Result<T>();
        result.success = false;
        result.errorMessage = errorMessage;
        return result;
    }

    public static <T> Result<T> fail(String errorCode, String errorMessage) {
        Result<T> result = new Result<T>();
        result.success = false;
        result.errorCode = errorCode;
        result.errorMessage = errorMessage;
        return result;
    }

    public static <T> Result<T> fail(Throwable t) {
        return fail(ErrorCodeEnum.UNKNOWN_ERROR.getErrDtlCode(), ErrorCodeEnum.UNKNOWN_ERROR.getDesc());
    }

    /**
     * Gets the value of traceId. *
     *
     * @return the value of traceId
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * Sets the traceId
     * <p>You can use getTraceId() to get the value of traceId</p>
     *
     * @param traceId traceId
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * Gets the value of data. *
     *
     * @return the value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data
     * <p>You can use getData() to get the value of data</p>
     *
     * @param data data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets the value of errorCode. *
     *
     * @return the value of errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the errorCode
     * <p>You can use getErrorCode() to get the value of errorCode</p>
     *
     * @param errorCode errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the value of errorMessage. *
     *
     * @return the value of errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the errorMessage
     * <p>You can use getErrorMessage() to get the value of errorMessage</p>
     *
     * @param errorMessage errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Result{" + "traceId='" + traceId + '\'' + ", data=" + data + ", success=" + success
                + ", errorCode='" + errorCode + '\'' + ", errorMessage='" + errorMessage + '\''
                + '}';
    }

}
