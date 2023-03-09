package cn.qingyumao.library.extension.autoconfig;

import cn.qingyumao.library.extension.BeanContainer;
import cn.qingyumao.library.extension.BeanContainerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class SpringBeanContainer implements ApplicationContextAware, BeanContainer, InitializingBean {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        BeanContainerHolder.set(this);
    }

    @Override
    public <T> Optional<T> getAdaptiveBean(Class<T> clazz) {
        try {
            final T bean = this.context.getBean(clazz);
            return Optional.ofNullable(bean);
        } catch (BeansException beansException) {
            log.warn(" Get {}  bean from Spring ApplicationContext has exception. {} ", clazz.getName(), beansException.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public <I, T> Optional<I> getAdaptiveBean(Class<I> iClazz, Class<T> tClass) {
        final Map<String, I> map = this.context.getBeansOfType(iClazz);
        if (!CollectionUtils.isEmpty(map)) {
            for (I i : map.values()) {

            }
        }
        return Optional.empty();
    }

    @Override
    public <I, T> Collection<I> getAdaptiveBeans(Class<I> iClazz, Class<T> tClass) {
        final Map<String, I> map = this.context.getBeansOfType(iClazz);
        Collection collection = new ArrayList();
        if (!CollectionUtils.isEmpty(map)) {
            for (I i : map.values()) {
                final ResolvableType resolvableType = ResolvableType.forClass(i.getClass());
                final Class<?> resolve = resolvableType.getInterfaces()[0].getGeneric(0).resolve();
                if (resolve != null && resolve.equals(tClass)) {
                    collection.add(i);
                }
            }
        }
        return collection;
    }
}