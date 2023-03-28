package cn.qingyumao.scaffold.ddd.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 聚合根工厂
 */
public class IdGeneratorMethodAdapter implements IdGenerator<Object> {


    private final Object targetBean;

    private final Method targetMethod;

    public IdGeneratorMethodAdapter(Object targetBean, Method targetMethod) {
        this.targetBean = targetBean;
        this.targetMethod = targetMethod;
    }

    /**
     * 获取泛型
     *
     * @return
     */
    public Class<?> getIdGeneratorGenericClass() {
        return targetMethod.getReturnType();
    }

    @Override
    public Object generate() {
        final Object returnObject;
        try {
            returnObject = this.targetMethod.invoke(targetBean);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return returnObject;
    }
}
