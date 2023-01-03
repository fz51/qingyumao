package cn.qingyumao.library.domain.event;

import java.util.Collection;

/**
 * @author fz51
 */
public interface DomainEventPublisher {

    /**
     * 发送领域事件
     *
     * @param events
     */
    void publishEvent(Collection<DomainEvent> events);

}
