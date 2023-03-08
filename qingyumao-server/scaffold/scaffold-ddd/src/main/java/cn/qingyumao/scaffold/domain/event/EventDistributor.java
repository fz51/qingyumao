package cn.qingyumao.scaffold.domain.event;

import java.util.Collection;

public interface EventDistributor {

    void distribute(Collection<AbstractDomainEvent> events);
}
