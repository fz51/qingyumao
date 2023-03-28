package cn.qingyumao.scaffold.ddd.execution;

import cn.qingyumao.scaffold.ddd.domain.IdGenerator;
import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.Map;

public class AggregateGeneratorHolder {

    private static final Map<Class<?>, AggregateGenerator<?, ?>> GENERATOR_CACHE = new HashMap<>();


    public static <C, AR> AggregateGenerator<C, AR> getAggregateGenerator(Class<C> cmdClazz) {

        return (AggregateGenerator<C, AR>) GENERATOR_CACHE.get(cmdClazz);
    }

    public static void setAggregateGenerator(AggregateGenerator<?, ?> aggregateGenerator) {
        Class<?> cmdClazz = Object.class;

        Class<?>[] aggregateGeneratorGenericClasses;
        if (aggregateGenerator instanceof AggregateGeneratorMethodAdapter) {
            aggregateGeneratorGenericClasses = ((AggregateGeneratorMethodAdapter) aggregateGenerator).getAggregateGeneratorGenericClasses();
            cmdClazz = aggregateGeneratorGenericClasses[0];

        } else {
            aggregateGeneratorGenericClasses = GenericTypeResolver.resolveTypeArguments(aggregateGenerator.getClass(), AggregateGenerator.class);
            if (aggregateGeneratorGenericClasses != null) {
                cmdClazz = aggregateGeneratorGenericClasses[0];
            }
        }
        final AggregateGenerator<?, ?> existed = GENERATOR_CACHE.get(cmdClazz);
        if (existed == null) {
            GENERATOR_CACHE.put(cmdClazz, aggregateGenerator);
        } else {
            throw new IllegalStateException(cmdClazz.getName() + " 存在多个生成器");
        }
    }
}
