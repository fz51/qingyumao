package cn.qingyumao.scaffold.ddd.domain.event;

/**
 *
 * @param <T>
 */
public class PayloadDomainEvent<T> extends AbstractDomainEvent {

    private final T payload;


    public PayloadDomainEvent(String traceId, EventCreator creator, T payload) {
        super(traceId, creator);
        this.payload = payload;
    }


    public T getPayload() {
        return payload;
    }
}
