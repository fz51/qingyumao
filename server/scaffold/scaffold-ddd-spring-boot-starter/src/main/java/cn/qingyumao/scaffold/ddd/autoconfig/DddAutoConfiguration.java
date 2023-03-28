package cn.qingyumao.scaffold.ddd.autoconfig;

import cn.qingyumao.scaffold.ddd.CommonMethodProcessor;
import cn.qingyumao.scaffold.ddd.domain.event.EventDistributor;
import cn.qingyumao.scaffold.ddd.domain.event.LocalEventDistributor;
import cn.qingyumao.scaffold.ddd.execution.CmdDispatcher;
import cn.qingyumao.scaffold.ddd.execution.CmdExecutorFactory;
import cn.qingyumao.scaffold.ddd.execution.DefaultCmdExecutorFactory;
import cn.qingyumao.scaffold.ddd.repository.DefaultMysqlAggregateRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;

@Configuration
@ComponentScan("cn.qingyumao.scaffold.ddd.execution")
public class DddAutoConfiguration {


    @Bean
    public CommonMethodProcessor commonMethodProcessor() {
        return new CommonMethodProcessor();
    }


    @ConditionalOnMissingBean(CmdDispatcher.class)
    @Bean
    public CmdDispatcher cmdDispatcher(CmdExecutorFactory cmdExecutorFactory) {
        return new CmdDispatcher(cmdExecutorFactory);
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
