package cn.qingyumao.library.util;

import java.io.Serializable;
import java.util.Collection;

/**
 * 结果包装对象
 *
 * @param <T>
 */
public class ResultWrapper<T> implements Serializable {

    /**
     * 响应是否成功
     */
    private Boolean success;
    /**
     * 错误代码。
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;

    private T data;

    protected ResultWrapper(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public ResultWrapper<T> setData(T data) {
        this.data = data;
        return this;
    }


    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errMessage) {
        this.errorMessage = errMessage;
    }

    @Override
    public String toString() {
        return "ResultWrapper [success=" + this.success + ", errorCode=" + this.errorCode + ", errorMessage=" + this.errorMessage + "]";
    }

    /**
     * 是否是数组集合
     *
     * @return
     */
    public boolean isCollection() {
        if (this.getData() instanceof Collection) {
            return true;
        }
        return false;
    }
}
