package cn.qingyumao.scaffold.ddd.repository;

import cn.qingyumao.scaffold.ddd.MethodAnnotation;
import cn.qingyumao.scaffold.ddd.annotation.AggregateLoader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AggregateLoaderMethodAnnotation implements MethodAnnotation {


    @Override
    public Class<? extends Annotation> support() {
        return AggregateLoader.class;
    }

    @Override
    public void adapter(Object targetBean, Method[] methods) {
        for (Method m : methods) {
            AggregateLoaderHolder.setAggregateLoader(new AggregateLoaderMethodAdapter(targetBean, m));
        }
    }
}
