package cn.qingyumao.scaffold.execution.annation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface LoadVoucher {

    String value() default "";
}
