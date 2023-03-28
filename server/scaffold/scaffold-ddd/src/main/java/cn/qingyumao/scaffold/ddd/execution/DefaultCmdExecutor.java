package cn.qingyumao.scaffold.ddd.execution;

import cn.hutool.core.util.ClassUtil;
import cn.qingyumao.scaffold.ddd.annotation.LoadVoucher;
import cn.qingyumao.scaffold.ddd.repository.AggregateCommit;
import cn.qingyumao.scaffold.ddd.repository.AggregateCommitHolder;
import cn.qingyumao.scaffold.ddd.repository.AggregateLoader;
import cn.qingyumao.scaffold.ddd.repository.AggregateLoaderHolder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DefaultCmdExecutor implements CmdExecutor<Object> {

    @Getter
    private final Class<?> cmdClazz;
    @Getter
    private final Class<?> aggregateRootClazz;

    private final AggregateGenerator aggregateRootGenerator;

    private AggregateCommit aggregateCommit;

    public DefaultCmdExecutor(Class<?> cmdClazz, Class<?> aggregateRootClazz) {
        this.cmdClazz = cmdClazz;
        this.aggregateRootClazz = aggregateRootClazz;
        this.aggregateRootGenerator = AggregateGeneratorHolder.getAggregateGenerator(cmdClazz);
        this.aggregateCommit = AggregateCommitHolder.getAggregateCommit(aggregateRootClazz);
    }


    @Override
    public void execute(Object cmd) {
        if (!cmd.getClass().equals(cmdClazz)) {
            throw new IllegalStateException("命令和执行器不匹配");
        }
        Object ar = null;
        final List<? extends ObtainAggregateRootPostProcessor<?, Object>> obtainAggregateRootPostProcessors = ObtainAggregateRootPostProcessorHolder.listObtainAggregateRootPostProcessor(cmdClazz);
        if (obtainAggregateRootPostProcessors != null) {
            for (ObtainAggregateRootPostProcessor aggregateRootPostProcessor : obtainAggregateRootPostProcessors) {
                ar = aggregateRootPostProcessor.beforeObtain(cmd);
                if (ar != null) {
                    break;
                }
            }
        }
        if (ar == null) {
            ar = obtainAggregateRoot(cmd);
        }
        if (ar == null) {
            throw new IllegalStateException("执行器获取领域聚合失败，领域对象不能为空");
        }
        if (obtainAggregateRootPostProcessors != null) {
            for (ObtainAggregateRootPostProcessor aggregateRootPostProcessor : obtainAggregateRootPostProcessors) {
                aggregateRootPostProcessor.afterObtain(cmd, ar);
            }
        }
        //
        log.debug("执行业务");
        executing(cmd, ar);
        log.debug("保存数据");
        aggregateCommit.commit(ar);

        // distributor.distribute(ar.getEvents());
    }

    protected void executing(Object cmd, Object ar) {

        final List<? extends BusinessExecutor<?, Object>> businessExecutors = BusinessExecutorHolder.listBusinessExecutor(cmd.getClass());

        if (!CollectionUtils.isEmpty(businessExecutors)) {
            for (BusinessExecutor businessExecutor : businessExecutors) {
                businessExecutor.doExecute(cmd, ar);
            }
        }

    }


    protected Object obtainAggregateRoot(Object cmd) {
        // 先尝试从数据库里获取
        Object aggregateRoot = loadARFromStorage(cmd);
        // 尝试在命令中加载

        if (aggregateRootGenerator != null) {
            final Object aggregateRootFromCmd = aggregateRootGenerator.generateAggregateRoot(cmd);
            if (aggregateRootFromCmd != null) {
                if (aggregateRoot != null) {
                    throw new IllegalStateException("命令中获取到了两个聚合对象，无法确定使用哪一个");
                }
                aggregateRoot = aggregateRootFromCmd;
            }
        }
        return aggregateRoot;
    }


    private Object loadARFromStorage(Object cmd) {
        Object aggregateRoot = null;
        // 获取方法中的LoadV
        final Field[] declaredFields = ClassUtil.getDeclaredFields(cmd.getClass());
        final List<Field> loadVoucherFields = Arrays.stream(declaredFields).filter(field -> field.isAnnotationPresent(LoadVoucher.class)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(loadVoucherFields)) {
            return null;
        }
        if (loadVoucherFields.size() > 1) {
            throw new IllegalStateException("一个命令不能有多个 LoadVoucher 修饰修饰");
        }
        //

        Field loadVoucherField = loadVoucherFields.get(0);
        loadVoucherField.setAccessible(true);

        final Class<?> loadVoucherFieldType = loadVoucherField.getType();

        // 拿到属性和只进行匹配
        final AggregateLoader loader = AggregateLoaderHolder.getAggregateLoader(loadVoucherFieldType, aggregateRootClazz);
        if (loader != null) {
            final Object value;
            try {
                value = loadVoucherField.get(cmd);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            aggregateRoot = loader.load(value);
        }
        return aggregateRoot;
    }

}
