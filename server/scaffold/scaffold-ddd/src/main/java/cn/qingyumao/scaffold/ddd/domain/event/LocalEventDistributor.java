package cn.qingyumao.scaffold.ddd.domain.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;

/**
 * 本地消息发送
 */
public class LocalEventDistributor implements EventDistributor, ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public void acceptEvent(Collection<AbstractDomainEvent> events) {

    }

    @Override
    public void persistEvent(Collection<AbstractDomainEvent> events) {

    }

    @Override
    public void distribute(Collection<AbstractDomainEvent> events) {
        for (AbstractDomainEvent event : events) {
            applicationContext.publishEvent(event);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
