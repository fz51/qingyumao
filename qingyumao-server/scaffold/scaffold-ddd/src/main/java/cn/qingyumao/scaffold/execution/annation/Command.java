package cn.qingyumao.scaffold.execution.annation;

import cn.qingyumao.scaffold.domain.AggregateRoot;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Command {

    /**
     * 聚合根的类型
     *
     * @return
     */
    Class<? extends AggregateRoot<?>> value();
}
