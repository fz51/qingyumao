package cn.qingyumao.scaffold.ddd.execution;

import cn.qingyumao.scaffold.ddd.domain.AggregateRoot;

@FunctionalInterface
public interface AggregateRootMatcher {

    Class<? extends AggregateRoot> matchAggregateRootType();
}
