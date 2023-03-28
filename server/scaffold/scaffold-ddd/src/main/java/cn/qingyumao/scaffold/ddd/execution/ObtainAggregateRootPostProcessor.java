package cn.qingyumao.scaffold.ddd.execution;

public interface ObtainAggregateRootPostProcessor<C, AR> {
    /**
     * 加载聚合根对象之前执行。如果返回了聚合对象，将使用这个对象
     *
     * @param cmd
     * @return
     */
    default AR beforeObtain(C cmd) {
        return null;
    }

    /**
     * 加载聚合根对象后执行
     *
     * @param ar
     */
    default void afterObtain(C cmd, AR ar) {

    }
}
