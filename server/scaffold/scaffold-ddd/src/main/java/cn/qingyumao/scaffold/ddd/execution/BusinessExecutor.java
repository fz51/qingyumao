package cn.qingyumao.scaffold.ddd.execution;

public interface BusinessExecutor<C, AR> {

    void doExecute(C cmd, AR ar);
}
