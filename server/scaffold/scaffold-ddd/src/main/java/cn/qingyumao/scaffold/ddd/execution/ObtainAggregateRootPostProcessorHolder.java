package cn.qingyumao.scaffold.ddd.execution;

import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObtainAggregateRootPostProcessorHolder {

    private static final Map<Class<?>, List<ObtainAggregateRootPostProcessor<?, ?>>> CACHE = new HashMap<>();


    public static <C, AR> List<ObtainAggregateRootPostProcessor<C, AR>> listObtainAggregateRootPostProcessor(Class<C> cmdClazz) {

        return (List) CACHE.get(cmdClazz);
    }

    public static void addObtainAggregateRootPostProcessor(ObtainAggregateRootPostProcessor<?, ?> postProcessor) {
        Class<?> cmdClazz = Object.class;

        Class<?>[] generatorGenericClasses;
        generatorGenericClasses = GenericTypeResolver.resolveTypeArguments(postProcessor.getClass(), ObtainAggregateRootPostProcessor.class);
        if (generatorGenericClasses != null) {
            cmdClazz = generatorGenericClasses[0];
        }
        List list = CACHE.get(cmdClazz);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(postProcessor);
        // order 排序
        AnnotationAwareOrderComparator.sort(list);
        CACHE.put(cmdClazz, list);
    }
}
