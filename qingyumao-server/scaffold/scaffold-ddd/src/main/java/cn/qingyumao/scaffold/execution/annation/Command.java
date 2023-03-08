package cn.qingyumao.scaffold.execution.annation;

import cn.qingyumao.scaffold.domain.AggregateRoot;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Command {

    /**
     * 聚合根的类型
     *
     * @return
     */
    Class<? extends AggregateRoot<?>> value();
}
