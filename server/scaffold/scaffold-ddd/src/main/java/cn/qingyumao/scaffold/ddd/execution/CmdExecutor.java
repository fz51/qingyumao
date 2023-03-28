package cn.qingyumao.scaffold.ddd.execution;

public interface CmdExecutor<C> {

    void execute(C cmd);

}
