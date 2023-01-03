package cn.qingyumao.library.domain.event;

import cn.qingyumao.library.event.EventSource;

import java.util.EventListener;

/**
 * 领域事件监听器
 *
 * @param <S>
 * @author fz51
 */
public interface DomainEventListener<S extends EventSource> extends EventListener {
    /**
     * 监听领域事件
     *
     * @param event
     */
    void onEvent(DomainEvent<S> event);


}
