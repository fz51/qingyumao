package cn.qingyumao.scaffold.ddd.execution;

/**
 * 聚合根工厂
 */
public interface AggregateGenerator<C, AR> {

    /**
     * 获取聚合根
     *
     * @param cmd
     * @return
     */
    AR generateAggregateRoot(C cmd);

}
