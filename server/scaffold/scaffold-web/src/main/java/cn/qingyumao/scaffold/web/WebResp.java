package cn.qingyumao.scaffold.web;

import cn.qingyumao.scaffold.common.ResultWrapper;

/**
 * 通用 web请求响应返回格式
 *
 * @param <T>
 */
public class WebResp<T> extends ResultWrapper {
    /**
     * 请求全局唯一id
     */
    private String traceId;

    /**
     * 请求有错误时，前端对 errorMessage 的处理方式：： 0 默认不处理; 1 警告消息提示; 2 错误消息提示; 4 通知; 9 错误页面提示
     */
    private int errorShowType;

    /**
     * 额外信息
     * 详情信息，提供问题排查使用。
     * 在开发和测试环境开启
     */
    private String debugMessage;

    private WebResp(T data) {
        super(data);
    }

    public int getErrorShowType() {
        return errorShowType;
    }

    public WebResp<T> setErrorShowType(int showType) {
        this.errorShowType = showType;
        return this;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public WebResp<T> setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
        return this;
    }

    public static WebResp buildFailure(String errCode, String errMessage) {
        WebResp response = new WebResp(null);
        response.setSuccess(false);
        response.setErrorCode(errCode);
        response.setErrorMessage(errMessage);
        return response;
    }

    public static <T> WebResp buildSuccess(T data) {
        WebResp<T> response = new WebResp<>(data);
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static WebResp buildSuccess() {
        WebResp response = new WebResp<>(null);
        response.setSuccess(true);
        return response;
    }
}