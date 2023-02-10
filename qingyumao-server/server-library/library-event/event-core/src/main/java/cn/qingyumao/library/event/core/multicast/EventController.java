package cn.qingyumao.library.event.core.multicast;

import cn.qingyumao.library.event.core.AbsDomainEvent;

public interface EventController {
    /**
     * 准备分发
     *
     * @param event
     */
    void prepareMulticast(AbsDomainEvent event);

    /**
     * 风发中
     * @param event
     */
    void multicasting(AbsDomainEvent event);

    void prepareMulticastq(AbsDomainEvent event);
}
