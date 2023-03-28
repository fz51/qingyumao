package cn.qingyumao.scaffold.ddd.domain.event.repository;

import cn.qingyumao.scaffold.ddd.domain.event.AbstractDomainEvent;
import cn.qingyumao.scaffold.ddd.domain.event.DomainEventRepository;

import java.util.Collection;

public class JpaDomainEventRepository implements DomainEventRepository {


    private final DomainEventDao domainEventDao;

    public JpaDomainEventRepository(DomainEventDao domainEventDao) {
        this.domainEventDao = domainEventDao;
    }

    @Override
    public boolean save(Collection<AbstractDomainEvent> events) {

        return false;
    }

    private DomainEventPersistent formatPersistent(AbstractDomainEvent event) {
        return new DomainEventPersistent();
    }
}
