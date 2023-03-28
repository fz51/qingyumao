package cn.qingyumao.scaffold.ddd.execution;

/**
 * 处理命令后
 */
public interface CmdResult<T> {
    T getResult();
}
