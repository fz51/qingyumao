package cn.qingyumao.scaffold.ddd;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.qingyumao.scaffold.ddd.domain.IdGeneratorMethodAnnotation;
import cn.qingyumao.scaffold.ddd.execution.*;
import cn.qingyumao.scaffold.ddd.repository.AggregateCommitMethodAnnotation;
import cn.qingyumao.scaffold.ddd.repository.AggregateLoaderMethodAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
public class CommonMethodProcessor implements BeanDefinitionRegistryPostProcessor, SmartInitializingSingleton, BeanFactoryPostProcessor {

    private ConfigurableListableBeanFactory beanFactory;


    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    @Override
    public void afterSingletonsInstantiated() {
        ConfigurableListableBeanFactory beanFactory = this.beanFactory;
        Assert.state(this.beanFactory != null, "No ConfigurableListableBeanFactory set");
        String[] beanNames = beanFactory.getBeanNamesForType(Object.class);
        final Map<String, MethodAnnotation> methodAnnotations = beanFactory.getBeansOfType(MethodAnnotation.class);

        for (String beanName : beanNames) {
            if (!ScopedProxyUtils.isScopedTarget(beanName)) {
                Class<?> type = null;
                try {
                    type = AutoProxyUtils.determineTargetClass(beanFactory, beanName);
                } catch (Throwable ex) {
                    // An unresolvable bean type, probably from a lazy bean - let's ignore it.
                    if (log.isDebugEnabled()) {
                        log.debug("Could not resolve target class for bean with name '" + beanName + "'", ex);
                    }
                }
                if (type != null) {
                    if (ScopedObject.class.isAssignableFrom(type)) {
                        try {
                            Class<?> targetClass = AutoProxyUtils.determineTargetClass(beanFactory, ScopedProxyUtils.getTargetBeanName(beanName));
                            if (targetClass != null) {
                                type = targetClass;
                            }
                        } catch (Throwable ex) {
                            // An invalid scoped proxy arrangement - let's ignore it.
                            if (log.isDebugEnabled()) {
                                log.debug("Could not resolve target bean for scoped proxy '" + beanName + "'", ex);
                            }
                        }
                    }
                    for (MethodAnnotation methodAnnotation : methodAnnotations.values()) {
                        final Class<? extends Annotation> interestedAnnotation = methodAnnotation.support();
                        final Method[] methods = ReflectUtil.getMethods(type, method -> AnnotationUtil.hasAnnotation(method, interestedAnnotation));
                        if (methods != null) {
                            methodAnnotation.adapter(beanFactory.getBean(beanName), methods);
                        }
                    }
                }
            }
        }
        // 注册 ObtainAggregateRootPostProcessor
        final Map<String, ObtainAggregateRootPostProcessor> obtainAggregateRootPostProcessorMap = beanFactory.getBeansOfType(ObtainAggregateRootPostProcessor.class);
        obtainAggregateRootPostProcessorMap.values().forEach(ObtainAggregateRootPostProcessorHolder::addObtainAggregateRootPostProcessor);

        final Map<String, BusinessExecutor> businessExecutorMap = beanFactory.getBeansOfType(BusinessExecutor.class);
        businessExecutorMap.values().forEach(BusinessExecutorHolder::addBusinessExecutor);


    }

    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) throws BeansException {
        Class<?>[] classes = new Class[]{IdGeneratorMethodAnnotation.class, AggregateGeneratorMethodAnnotation.class, AggregateCommitMethodAnnotation.class, AggregateLoaderMethodAnnotation.class};
        for (Class<?> c : classes) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(c);
            registry.registerBeanDefinition(c.getName(), builder.getBeanDefinition());
        }
    }
}
