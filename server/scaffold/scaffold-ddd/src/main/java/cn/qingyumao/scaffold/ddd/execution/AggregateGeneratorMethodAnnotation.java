package cn.qingyumao.scaffold.ddd.execution;

import cn.qingyumao.scaffold.ddd.MethodAnnotation;
import cn.qingyumao.scaffold.ddd.annotation.AggregateRootGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AggregateGeneratorMethodAnnotation implements MethodAnnotation {


    @Override
    public Class<? extends Annotation> support() {
        return AggregateRootGenerator.class;
    }

    @Override
    public void adapter(Object targetBean, Method[] methods) {
        for (Method m : methods) {
            AggregateGeneratorHolder.setAggregateGenerator(new AggregateGeneratorMethodAdapter(targetBean, m));
        }
    }
}
