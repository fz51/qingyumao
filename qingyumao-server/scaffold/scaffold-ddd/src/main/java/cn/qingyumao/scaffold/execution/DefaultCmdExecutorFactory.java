package cn.qingyumao.scaffold.execution;

import cn.hutool.core.util.ReflectUtil;
import cn.qingyumao.scaffold.domain.event.EventDistributor;
import cn.qingyumao.scaffold.repository.AggregateLoader;
import cn.qingyumao.scaffold.repository.AggregateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DefaultCmdExecutorFactory implements BeanFactoryPostProcessor {


    @Nullable
    private ConfigurableListableBeanFactory beanFactory;

    public DefaultCmdExecutor createCmdExecutor(Class<?> cmdClazz, Class<?> aggregateRootClazz) {
        final DefaultCmdExecutor defaultCmdExecutor = new DefaultCmdExecutor(cmdClazz, aggregateRootClazz);

        defaultCmdExecutor.setAggregateLoader(matchAggregateLoader(cmdClazz, aggregateRootClazz));
        defaultCmdExecutor.setAggregateRepository(matchAggregateRepository(cmdClazz, aggregateRootClazz));

        defaultCmdExecutor.setBusinessExecutors(matchBusinessExecutors(cmdClazz, aggregateRootClazz));

        defaultCmdExecutor.setDistributor(beanFactory.getBean(EventDistributor.class));

        defaultCmdExecutor.setAggregateRootPostProcessors(matchAggregateRootPostProcessors(cmdClazz, aggregateRootClazz));
        defaultCmdExecutor.setBusinessExecutePostProcessors(matchBusinessExecutePostProcessors(cmdClazz, aggregateRootClazz));

        defaultCmdExecutor.setAggregateRootGenerator(matchAggregateRootGenerator(cmdClazz, aggregateRootClazz));
        return defaultCmdExecutor;
    }

    private AggregateGenerator matchAggregateRootGenerator(Class<?> cmdClazz, Class<?> aggregateRootClazz) {
        final Map<String, AggregateGenerator> beans = beanFactory.getBeansOfType(AggregateGenerator.class);
        // 获取类
        if (CollectionUtils.isEmpty(beans)) {
            throw new IllegalStateException("命令" + cmdClazz.getName() + " 找不到对应的 AggregateGenerator");
        }
        AggregateGenerator generator = null;
        for (AggregateGenerator value : beans.values()) {
            if (matchAggregateRootGenerator(value, cmdClazz, aggregateRootClazz)) {
                if (generator != null) {
                    throw new IllegalStateException("请确保一个命令只能对应一个聚合根生成器");
                }
                generator = value;
            }
        }
        return generator;
    }

    private boolean matchAggregateRootGenerator(AggregateGenerator<?, ?> generator, Class<?> cmdClazz, Class<?> aggregateRootClazz) {
        final Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(generator.getClass(), AggregateGenerator.class);
        if (classes != null && classes.length == 2) {
            return classes[0].equals(cmdClazz) && classes[1].equals(aggregateRootClazz);
        }
        return false;
    }


    private List<BusinessExecutePostProcessor> matchBusinessExecutePostProcessors(Class<?> cmdClazz, Class<?> aggregateRootClazz) {

        return null;
    }

    private List<ObtainAggregateRootPostProcessor> matchAggregateRootPostProcessors(Class<?> cmdClazz, Class<?> aggregateRootClazz) {
        return null;
    }

    private Map<Class<?>, AggregateLoader> matchAggregateLoader(Class<?> cmdClazz, Class<?> aggregateRootClazz) {
        return null;
    }

    private List<BusinessExecutor> matchBusinessExecutors(Class<?> cmdClazz, Class<?> aggregateRootClazz) {
        List<BusinessExecutor> result = new ArrayList<>();
        final Map<String, BusinessExecutor> beansOfType = beanFactory.getBeansOfType(BusinessExecutor.class);
        // 排序

        if (!CollectionUtils.isEmpty(beansOfType)) {
            for (BusinessExecutor businessExecutor : beansOfType.values()) {
                final Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(businessExecutor.getClass(), BusinessExecutor.class);
                if (classes == null) {
                    continue;
                }
                if (classes.length != 2) {
                    continue;
                }
                if (classes[0].equals(cmdClazz) && classes[1].equals(aggregateRootClazz)) {
                    result.add(businessExecutor);

                }
            }
        }
        return result;
    }


    private AggregateRepository matchAggregateRepository(Class<?> cmdClazz, Class<?> aggregateRootClazz) {
        AggregateRepository aggregateRepository = null;
        final Map<String, AggregateRepository> repositoryMap = beanFactory.getBeansOfType(AggregateRepository.class);
        // 获取类
        if (CollectionUtils.isEmpty(repositoryMap)) {
            throw new IllegalStateException("命令" + cmdClazz.getName() + " 找不到对应的AggregateRepository");
        }

        for (AggregateRepository value : repositoryMap.values()) {
            if (matchAggregateRootClass(value, aggregateRootClazz)) {
                if (aggregateRepository != null) {
                    throw new IllegalStateException("请确保一个聚合根只能对应一个AggregateRepository ");
                }
                aggregateRepository = value;
            }
        }

        return aggregateRepository;
    }

    private boolean matchAggregateRootClass(AggregateRepository repository, Class<?> aggregateRootClazz) {
        if (repository instanceof AggregateRootMatcher) {
            return ((AggregateRootMatcher) repository).matchAggregateRootType().equals(aggregateRootClazz);
        }
        final Method m = ReflectUtil.getMethod(repository.getClass(), "save", aggregateRootClazz);
        return m != null;
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
