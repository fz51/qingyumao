package cn.qingyumao.scaffold.execution;

/**
 * 处理命令后
 */
public interface CmdResult<T> {
    T getResult();
}
