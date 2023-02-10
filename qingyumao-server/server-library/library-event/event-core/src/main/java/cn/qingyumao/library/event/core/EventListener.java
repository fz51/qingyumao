package cn.qingyumao.library.event.core;

/**
 * 领域事件监听器
 *
 * @param <E>
 * @author fz51
 */
public interface EventListener<E extends AbsDomainEvent<?>> extends java.util.EventListener {
    /**
     * 监听领域事件
     *
     * @param event
     */
    void onEvent(E event);


}
