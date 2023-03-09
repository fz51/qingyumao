package cn.qingyumao.library.event.core.multicast;

import cn.qingyumao.library.event.core.AbsDomainEvent;

/**
 * 事件发送
 */
public interface EventMulticaster {

    void multicastEvent(AbsDomainEvent event);
}
