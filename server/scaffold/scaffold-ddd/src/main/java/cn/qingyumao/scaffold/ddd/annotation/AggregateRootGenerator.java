package cn.qingyumao.scaffold.ddd.annotation;

import java.lang.annotation.*;

/**
 * 领域聚合根生成器
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AggregateRootGenerator {
}
