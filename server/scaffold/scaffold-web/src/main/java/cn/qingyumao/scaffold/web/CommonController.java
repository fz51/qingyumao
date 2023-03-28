package cn.qingyumao.scaffold.web;

import cn.qingyumao.scaffold.ddd.execution.CmdDispatcher;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.Assert;

/**
 * 通用
 */
public class CommonController implements SmartInitializingSingleton, BeanFactoryPostProcessor {

    private ConfigurableListableBeanFactory beanFactory;

    private CmdDispatcher cmdDispatcher;


    public void handleCmd(Object cmd) {
        cmdDispatcher.dispatch(cmd);
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.cmdDispatcher = beanFactory.getBean(CmdDispatcher.class);
        Assert.notNull(cmdDispatcher, "cmdDispatcher is not null");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
