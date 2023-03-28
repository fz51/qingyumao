package cn.qingyumao.scaffold.ddd.repository;

import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.Map;

public class AggregateCommitHolder {

    private static final Map<Class<?>, AggregateCommit<?>> CACHE = new HashMap<>();


    public static <AR> AggregateCommit<AR> getAggregateCommit(Class<AR> arClazz) {

        return (AggregateCommit<AR>) CACHE.get(arClazz);
    }

    public static <AR> void setAggregateCommit(AggregateCommit<AR> aggregateCommit) {

        Class<?> arClazz = Object.class;
        Class<?>[] aggregateGeneratorGenericClasses;
        if (aggregateCommit instanceof AggregateCommitMethodAdapter) {
            aggregateGeneratorGenericClasses = ((AggregateCommitMethodAdapter) aggregateCommit).getGeneratorGenericClasses();
            arClazz = aggregateGeneratorGenericClasses[0];

        } else {
            aggregateGeneratorGenericClasses = GenericTypeResolver.resolveTypeArguments(aggregateCommit.getClass(), AggregateLoader.class);
            if (aggregateGeneratorGenericClasses != null) {
                arClazz = aggregateGeneratorGenericClasses[0];

            }
        }

        final AggregateCommit<?> existed = CACHE.get(arClazz);
        if (existed == null) {
            CACHE.put(arClazz, aggregateCommit);
        } else {
            throw new IllegalStateException(arClazz.getName() + " 存在多个保存器");
        }
    }
}
