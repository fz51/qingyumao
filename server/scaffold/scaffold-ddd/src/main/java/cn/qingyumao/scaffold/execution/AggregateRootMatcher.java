package cn.qingyumao.scaffold.execution;

import cn.qingyumao.scaffold.domain.AggregateRoot;

@FunctionalInterface
public interface AggregateRootMatcher {

    Class<? extends AggregateRoot> matchAggregateRootType();
}
