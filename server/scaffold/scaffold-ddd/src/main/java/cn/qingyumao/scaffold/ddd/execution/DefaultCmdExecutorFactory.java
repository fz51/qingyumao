package cn.qingyumao.scaffold.ddd.execution;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultCmdExecutorFactory {

    public DefaultCmdExecutor createCmdExecutor(Class<?> cmdClazz, Class<?> aggregateRootClazz) {
        return new DefaultCmdExecutor(cmdClazz, aggregateRootClazz);
    }

}
