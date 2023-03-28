package cn.qingyumao.scaffold.ddd.execution;

public interface BusinessExecutePostProcessor<C, AR> {

    void postProcessBeforeDoExecute(C cmd, AR ar);

    void postProcessAfterDoExecute(C cmd, AR ar);
}
