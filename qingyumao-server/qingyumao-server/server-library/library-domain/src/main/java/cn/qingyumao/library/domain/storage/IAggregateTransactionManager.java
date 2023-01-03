package cn.qingyumao.library.domain.storage;

public interface IAggregateTransactionManager {

    /**
     * 开启事物
     */
    void begin();

    /**
     * 提交事物
     */
    void commit();

    /**
     * 回滚
     */
    void rollback();

}
