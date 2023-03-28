package cn.qingyumao.scaffold.ddd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public interface MethodAnnotation {

    Class<? extends Annotation> support();

    void adapter(Object targetBean, Method[] methods);
}
