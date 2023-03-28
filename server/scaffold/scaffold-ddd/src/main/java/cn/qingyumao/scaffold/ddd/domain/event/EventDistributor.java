package cn.qingyumao.scaffold.ddd.domain.event;

import java.util.Collection;

/**
 * 消息分发器
 */
public interface EventDistributor {

    /**
     * 接受消息
     */
    void acceptEvent(Collection<AbstractDomainEvent> events);

    /**
     * 持久化消息
     *
     * @param events
     */
    void persistEvent(Collection<AbstractDomainEvent> events);

    /**
     * 分发消息
     *
     * @param events
     */
    void distribute(Collection<AbstractDomainEvent> events);

}
