package cn.qingyumao.scaffold.ddd.repository;

import cn.qingyumao.scaffold.ddd.MethodAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AggregateCommitMethodAnnotation implements MethodAnnotation {


    @Override
    public Class<? extends Annotation> support() {
        return cn.qingyumao.scaffold.ddd.annotation.AggregateCommit.class;
    }

    @Override
    public void adapter(Object targetBean, Method[] methods) {
        for (Method m : methods) {
            AggregateCommitHolder.setAggregateCommit(new AggregateCommitMethodAdapter(targetBean, m));
        }
    }
}
