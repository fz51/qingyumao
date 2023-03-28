package cn.qingyumao.scaffold.ddd.annotation;

import java.lang.annotation.*;

/**
 * 表示这个对象是一个聚合持久化功能实例。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AggregateRepository {
}
