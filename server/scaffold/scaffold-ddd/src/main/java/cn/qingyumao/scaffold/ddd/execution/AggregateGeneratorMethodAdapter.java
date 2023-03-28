package cn.qingyumao.scaffold.ddd.execution;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 聚合根工厂
 */
public class AggregateGeneratorMethodAdapter implements AggregateGenerator<Object, Object> {


    private final Object targetBean;

    private final Method targetMethod;

    public AggregateGeneratorMethodAdapter(Object targetBean, Method targetMethod) {
        this.targetBean = targetBean;
        this.targetMethod = targetMethod;
    }

    public Class<?>[] getAggregateGeneratorGenericClasses() {
        return new Class[]{targetMethod.getParameterTypes()[0], targetMethod.getReturnType()};
    }

    @Override
    public Object generateAggregateRoot(Object cmd) {
        final Object returnObject;
        try {
            returnObject = this.targetMethod.invoke(targetBean, cmd);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return returnObject;
    }
}
