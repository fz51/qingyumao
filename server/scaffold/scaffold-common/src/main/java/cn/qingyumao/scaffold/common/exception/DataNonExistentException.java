package cn.qingyumao.scaffold.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 数据不存在异常。
 * 经过数据验证（查找数据库）的参数找不到对应的数据，非法数据异常。
 */
public class DataNonExistentException extends DataErrorBusinessException {

    public DataNonExistentException(String s, Object... param) {
        super(StrUtil.format(s, param));
    }

}
