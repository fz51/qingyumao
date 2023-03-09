package cn.qingyumao.scaffold.domain.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.EventObject;
import java.util.UUID;

/**
 * 事务事件
 */
public abstract class AbstractDomainEvent extends EventObject {

    /**
     * 事件id。每个事件发生，产生一个唯一值
     */
    @Getter
    private final String id = UUID.randomUUID().toString();

    /**
     * 可以通过此值来进行追踪事件的。
     */
    @Getter
    private final String traceId;

    /**
     * 产生事件的时间
     */
    @Getter
    private final LocalDateTime timestamp = LocalDateTime.now();
    /**
     * 事件创建对象
     */
    @Getter
    private final EventCreator creator;

    @Getter
    @Setter
    private EventStatus status;

    public AbstractDomainEvent(String traceId, EventCreator creator) {
        super("null");
        this.traceId = traceId;
        this.creator = creator;
        this.status = EventStatus.CREATED;
    }
}
