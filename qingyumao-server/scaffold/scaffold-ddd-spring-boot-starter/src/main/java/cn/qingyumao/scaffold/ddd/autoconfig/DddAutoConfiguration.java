package cn.qingyumao.scaffold.ddd.autoconfig;

import cn.qingyumao.scaffold.domain.event.LocalEventDistributor;
import cn.qingyumao.scaffold.domain.event.EventDistributor;
import cn.qingyumao.scaffold.execution.CmdDispatcher;
import cn.qingyumao.scaffold.execution.CmdExecutorFactory;
import cn.qingyumao.scaffold.execution.DefaultCmdExecutorFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("cn.qingyumao.scaffold.execution")
public class DddAutoConfiguration {


    @ConditionalOnMissingBean(CmdDispatcher.class)
    @Bean
    public CmdDispatcher cmdDispatcher() {
        return new CmdDispatcher(cmdExecutorFactory());
    }

    @Bean
    public CmdExecutorFactory cmdExecutorFactory() {
        return new CmdExecutorFactory();
    }

    @ConditionalOnMissingBean(DefaultCmdExecutorFactory.class)
    @Bean
    public DefaultCmdExecutorFactory defaultCmdExecutorFactory() {
        return new DefaultCmdExecutorFactory();
    }

    @ConditionalOnMissingBean(EventDistributor.class)
    @Bean
    public EventDistributor eventDistributor() {
        return new LocalEventDistributor();
    }
}
