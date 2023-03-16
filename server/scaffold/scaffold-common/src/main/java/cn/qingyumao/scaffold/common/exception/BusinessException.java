package cn.qingyumao.scaffold.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String s) {
        super(s);
    }

    public BusinessException(String s, Object... param) {
        super(StrUtil.format(s, param));
    }


    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
