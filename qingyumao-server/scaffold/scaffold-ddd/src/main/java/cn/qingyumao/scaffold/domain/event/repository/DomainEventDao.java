package cn.qingyumao.scaffold.domain.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainEventDao extends JpaRepository<DomainEventPersistent, Long> {
}
