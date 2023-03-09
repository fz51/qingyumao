package cn.qingyumao.scaffold.execution;

public interface CmdExecutor<C> {

    void execute(C cmd);

}
