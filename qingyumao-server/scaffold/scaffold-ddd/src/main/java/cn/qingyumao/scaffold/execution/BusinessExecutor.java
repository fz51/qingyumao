package cn.qingyumao.scaffold.execution;

import cn.qingyumao.scaffold.domain.AggregateRoot;

public interface BusinessExecutor<C,AR extends AggregateRoot<?>> {

    void doExecute(C cmd, AR ar);
}
