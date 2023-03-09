package cn.qingyumao.scaffold.execution;

import cn.qingyumao.scaffold.domain.AggregateRoot;

/**
 * 聚合根工厂
 */
public interface AggregateGenerator<C,AR extends AggregateRoot<?>> {

    /**
     * 获取聚合根
     *
     * @param cmd
     * @return
     */
     AR generateAggregateRoot(C cmd);

}
