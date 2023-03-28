package cn.qingyumao.scaffold.ddd.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface LoadVoucher {

    String value() default "";
}
