package cn.qingyumao.scaffold.domain.event;

import java.util.Collection;

public interface DomainEventRegister {

    void register(Collection<Object> events);

}
