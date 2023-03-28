package cn.qingyumao.scaffold.ddd.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 聚合根工厂
 */
public class AggregateCommitMethodAdapter implements AggregateCommit<Object> {


    private final Object targetBean;

    private final Method targetMethod;

    public AggregateCommitMethodAdapter(Object targetBean, Method targetMethod) {
        this.targetBean = targetBean;
        this.targetMethod = targetMethod;
    }

    public Class<?>[] getGeneratorGenericClasses() {
        return new Class[]{targetMethod.getParameterTypes()[0]};
    }

    @Override
    public void commit(Object ar) {
        try {
            this.targetMethod.invoke(targetBean, ar);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
