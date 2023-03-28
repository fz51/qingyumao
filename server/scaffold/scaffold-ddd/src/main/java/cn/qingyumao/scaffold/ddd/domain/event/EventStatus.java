package cn.qingyumao.scaffold.ddd.domain.event;

/**
 * 事件状态
 */
public enum EventStatus {
    /**
     * 事件刚刚创建出来的
     */
    CREATED,
    /**
     * 已持久
     */
    PERSISTENT,
    /**
     * 已发布
     */
    PUBLISHED,
    /**
     * 消费中
     */
    IN_CONSUMPTION,
    /**
     * 消费完成。可以删除了
     */
    CONSUMPTION_COMPLETED,

}
