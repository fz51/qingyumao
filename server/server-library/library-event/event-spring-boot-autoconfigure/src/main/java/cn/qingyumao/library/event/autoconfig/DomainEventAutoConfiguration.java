package cn.qingyumao.library.event.autoconfig;

import cn.qingyumao.library.event.core.DomainEventPublisher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainEventAutoConfiguration {


    @ConditionalOnMissingBean(DomainEventPublisher.class)
    @Bean
    public DomainEventPublisher domainEventPublisher() {
        return new DomainEventPublisher();
    }
}
