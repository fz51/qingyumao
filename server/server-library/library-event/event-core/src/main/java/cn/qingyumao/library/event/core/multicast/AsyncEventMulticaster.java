package cn.qingyumao.library.event.core.multicast;

import cn.qingyumao.library.event.core.AbsDomainEvent;
import cn.qingyumao.library.event.core.EventListener;
import cn.qingyumao.library.extension.BeanContainerHolder;

import java.util.Collection;

/**
 * 异步事件分发
 */
public class AsyncEventMulticaster implements EventMulticaster {

    private EventController eventController;

    protected Collection<EventListener> getEventListeners(AbsDomainEvent event) {
        return BeanContainerHolder.get().getAdaptiveBeans(EventListener.class, event.getClass());
    }

    @Override
    public void multicastEvent(AbsDomainEvent event) {
        final Collection<EventListener> listeners = getEventListeners(event);
        if (listeners != null) {
            // 获取事件的监听器
            for (EventListener listener : listeners) {
                // 另外一个线程来进行启动
                this.invokeListener(listener, event);
            }
        }

    }

    private void invokeListener(EventListener listener, AbsDomainEvent event) {
        try {
            listener.onEvent(event);
        } catch (Throwable t) {

        }
    }
}
