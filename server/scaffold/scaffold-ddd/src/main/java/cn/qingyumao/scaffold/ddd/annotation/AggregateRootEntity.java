package cn.qingyumao.scaffold.ddd.annotation;

import java.lang.annotation.*;

/**
 * 聚合根
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AggregateRootEntity {

}
