package cn.qingyumao.scaffold.execution;

import cn.qingyumao.scaffold.domain.AggregateRoot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CmdExecutorFactory implements SmartInitializingSingleton, BeanFactoryPostProcessor {
    // key: Class<cmdClassType>
    // value: CmdExecutor
    private Map<Class<?>, CmdExecutor<?>> cmdExecutorHolder = new HashMap<>();

    @Nullable
    private ConfigurableListableBeanFactory beanFactory;

    private DefaultCmdExecutorFactory defaultCmdExecutorFactory;

    public <C> CmdExecutor<C> get(Class<C> cmdClazz, Class<? extends AggregateRoot<?>> aggregateRootClazz) {

        CmdExecutor executor = cmdExecutorHolder.get(cmdClazz);

        if (executor == null) {
            // 创建初始的命令
            executor = defaultCmdExecutorFactory.createCmdExecutor(cmdClazz, aggregateRootClazz);

            cmdExecutorHolder.put(cmdClazz, executor);
        }
        return executor;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.defaultCmdExecutorFactory = beanFactory.getBean(DefaultCmdExecutorFactory.class);
        // beanFactory.getBean();
        final Map<String, CmdExecutor> executorMap = beanFactory.getBeansOfType(CmdExecutor.class);
        for (CmdExecutor executor : executorMap.values()) {
            final Class cmdClazz = findCmdTypeByExecutor(executor);
            if (cmdClazz != null) {
                final CmdExecutor existedExecutor = cmdExecutorHolder.get(cmdClazz);
                if (existedExecutor != null) {
                    log.error("一个命令 {} 不能定义两个执行器。已存在：{},{}", cmdClazz.getName(), existedExecutor.getClass().getName(), executor.getClass().getName());
                    throw new IllegalStateException("一个命令不能定义两个执行器");
                } else {
                    cmdExecutorHolder.put(cmdClazz, executor);
                }
            } else {
                log.warn("executor bean ：{} 没有确定泛型", executor.getClass().getName());
            }

        }
    }

    private Class<?> findCmdTypeByExecutor(CmdExecutor<?> executor) {
        return GenericTypeResolver.resolveTypeArgument(executor.getClass(), CmdExecutor.class);
    }
}
