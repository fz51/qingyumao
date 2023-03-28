package cn.qingyumao.scaffold.ddd.domain.event;

import java.util.Collection;

public interface DomainEventRegister {

    void register(Collection<Object> events);

}
