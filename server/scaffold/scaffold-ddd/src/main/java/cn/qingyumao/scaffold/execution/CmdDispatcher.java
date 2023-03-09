package cn.qingyumao.scaffold.execution;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.qingyumao.scaffold.domain.AggregateRoot;
import cn.qingyumao.scaffold.execution.annation.Command;

public class CmdDispatcher {

    private final CmdExecutorFactory cmdExecutorFactory;

    public CmdDispatcher(CmdExecutorFactory cmdExecutorFactory) {
        this.cmdExecutorFactory = cmdExecutorFactory;
    }

    public <C> void dispatch(C command) {

        CmdExecutor<C> executor = cmdExecutorFactory.get((Class<C>) command.getClass(), findAggregateRootClazz(command));
        if (executor == null) {
            throw new IllegalStateException("找不到执行器");
        }
        executor.execute(command);
    }

    private Class<? extends AggregateRoot<?>> findAggregateRootClazz(Object command) {
        final Class<? extends AggregateRoot<?>> aggregateRootClazz = AnnotationUtil.getAnnotationValue(command.getClass(), Command.class);
        if (aggregateRootClazz == null) {
            throw new IllegalStateException("命令必须需要 @Command 标注");
        }
        return aggregateRootClazz;
    }

}
