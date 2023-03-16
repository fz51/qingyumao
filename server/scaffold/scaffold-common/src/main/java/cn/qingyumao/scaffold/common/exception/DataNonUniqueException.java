package cn.qingyumao.scaffold.common.exception;

/**
 * 非唯一数据异常
 */
public class DataNonUniqueException extends DataErrorBusinessException {
    public DataNonUniqueException(String s, Object... param) {
        super(s, param);
    }
}
