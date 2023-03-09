package cn.qingyumao.scaffold.web.autoconfig;

import cn.qingyumao.scaffold.web.ControllerExceptionAdvice;
import cn.qingyumao.scaffold.web.ControllerResponseAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoWebConfiguration {

    @ConditionalOnMissingBean(ControllerExceptionAdvice.class)
    @Bean
    public ControllerExceptionAdvice controllerExceptionAdvice() {
        return new ControllerExceptionAdvice();
    }

    @ConditionalOnMissingBean(ControllerResponseAdvice.class)
    @Bean
    public ControllerResponseAdvice controllerResponseAdvice() {
        return new ControllerResponseAdvice();
    }


}
