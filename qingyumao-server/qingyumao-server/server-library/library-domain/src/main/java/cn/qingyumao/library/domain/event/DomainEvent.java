package cn.qingyumao.library.domain.event;

import cn.qingyumao.library.event.EventSource;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.EventObject;

/**
 * 事务事件
 */
public class DomainEvent<R extends EventSource> extends EventObject {

    /**
     * 事件id
     */
    @Getter
    private String id;

    /**
     * 可以通过此值来进行追踪事件的。
     */
    @Getter
    private String traceId;

    /**
     * 产生事件的时间
     */
    @Getter
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * 事件来源
     */
    @Getter
    private R source;

    /**
     * 事件创建对象
     */
    @Getter
    private EventCreator creator;

    public DomainEvent(R resource, EventCreator creator) {
        super(resource);
        this.source = resource;
        this.creator = creator;
    }

    public DomainEvent(String traceId, R resource, EventCreator creator) {
        super(resource);
        this.traceId = traceId;
        this.source = resource;
        this.creator = creator;
    }
}
