package cn.qingyumao.library.event.core;

import cn.qingyumao.library.event.core.multicast.AsyncEventMulticaster;
import cn.qingyumao.library.event.core.multicast.EventMulticaster;

import java.util.Collection;

/**
 * 领域事件发送器
 *
 * @author fz51
 */
public class DomainEventPublisher {

    private EventMulticaster eventMulticaster;

    public DomainEventPublisher() {
        this.eventMulticaster = new AsyncEventMulticaster();
    }


    /**
     * 发送领域事件
     *
     * @param events
     */
    public void publishEvent(Collection<Object> events) {
        events.forEach(e -> {
            if (e instanceof AbsDomainEvent<?>) {
                getEventMulticaster().multicastEvent((AbsDomainEvent) e);
            } else {
                getEventMulticaster().multicastEvent(new DefaultEvent(e));
            }
        });
    }

    EventMulticaster getEventMulticaster() {
        if (this.eventMulticaster == null) {
            throw new IllegalStateException("eventMulticaster is not null");
        }
        return this.eventMulticaster;
    }

}
