package cn.qingyumao.library.event.core;

import java.util.Collection;

public interface DomainEventStorage {

    /**
     * 保存领域事件
     *
     * @param events
     */
    default void save(Collection<Object> events) {

    }

    /**
     * 删除领域事件
     *
     * @param events
     */
    default void remove(Collection<Object> events) {

    }

}
