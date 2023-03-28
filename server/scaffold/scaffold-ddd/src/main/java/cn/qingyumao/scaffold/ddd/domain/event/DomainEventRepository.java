package cn.qingyumao.scaffold.ddd.domain.event;

import java.util.Collection;

public interface DomainEventRepository {
    /**
     * 保存领域业务处理的过程中产生的事件
     *
     * @param events
     * @return
     */
    boolean save(Collection<AbstractDomainEvent> events);


}
