package cn.qingyumao.scaffold.execution;

import cn.qingyumao.scaffold.domain.AggregateRoot;

public interface BusinessExecutePostProcessor<C,AR extends AggregateRoot<?>> {

    void postProcessBeforeDoExecute(C cmd, AR ar);

    void postProcessAfterDoExecute(C cmd, AR ar);
}
