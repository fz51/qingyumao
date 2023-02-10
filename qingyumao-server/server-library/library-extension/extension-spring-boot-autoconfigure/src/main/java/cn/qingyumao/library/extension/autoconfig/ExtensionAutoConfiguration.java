package cn.qingyumao.library.extension.autoconfig;

import cn.qingyumao.library.extension.BeanContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtensionAutoConfiguration {

    @ConditionalOnMissingBean(BeanContainer.class)
    @Bean
    public BeanContainer beanContainer() {
        return new SpringBeanContainer();
    }
}
