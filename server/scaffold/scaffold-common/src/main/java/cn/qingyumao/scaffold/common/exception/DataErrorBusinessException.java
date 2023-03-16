package cn.qingyumao.scaffold.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 数据错误异常
 */
public class DataErrorBusinessException extends BusinessException {

    public DataErrorBusinessException(String s, Object... param) {
        super(StrUtil.format(s, param));
    }


}
