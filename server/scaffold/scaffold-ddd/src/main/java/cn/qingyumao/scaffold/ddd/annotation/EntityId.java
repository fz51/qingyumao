package cn.qingyumao.scaffold.ddd.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@BusinessId
public @interface EntityId {

}
