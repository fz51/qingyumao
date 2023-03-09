package cn.qingyumao.library.event.core;

import java.util.Collection;

/**
 * 持久话事件
 */
public interface EventStorage {

    /**
     * 新增事件
     *
     * @param event
     */
    boolean addEvent(AbsDomainEvent event);

    /**
     * 获取待处理的事件
     *
     * @return
     */
    Collection<AbsDomainEvent> getEvents();

    void removeEvent(String eventId);

}
