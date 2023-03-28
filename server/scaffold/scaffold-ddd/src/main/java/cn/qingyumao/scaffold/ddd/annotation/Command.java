package cn.qingyumao.scaffold.ddd.annotation;

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
    Class<?> value();
}
