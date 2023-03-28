package cn.qingyumao.scaffold.ddd.domain;

import cn.qingyumao.scaffold.ddd.MethodAnnotation;
import cn.qingyumao.scaffold.ddd.annotation.IdGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class IdGeneratorMethodAnnotation implements MethodAnnotation {


    @Override
    public Class<? extends Annotation> support() {
        return IdGenerator.class;
    }

    @Override
    public void adapter(Object targetBean, Method[] methods) {
        for (Method m : methods) {
            IDGeneratorHolder.setIdGenerator(new IdGeneratorMethodAdapter(targetBean, m));
        }
    }
}
