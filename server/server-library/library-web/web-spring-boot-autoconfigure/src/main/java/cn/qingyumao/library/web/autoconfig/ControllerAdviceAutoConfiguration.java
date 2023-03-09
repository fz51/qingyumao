package cn.qingyumao.library.web.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerAdviceAutoConfiguration {

    @ConditionalOnMissingBean(ControllerExpAdvice.class)
    @Bean
    public ControllerExpAdvice controllerExpAdvice() {
        return new ControllerExpAdvice();
    }

    @ConditionalOnMissingBean(ControllerRespAdvice.class)
    @Bean
    public ControllerRespAdvice controllerRespAdvice() {
        return new ControllerRespAdvice();
    }

}
