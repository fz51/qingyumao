package cn.qingyumao.scaffold.execution;

import cn.hutool.core.util.ClassUtil;
import cn.qingyumao.scaffold.domain.AggregateRoot;
import cn.qingyumao.scaffold.domain.event.EventDistributor;
import cn.qingyumao.scaffold.execution.annation.LoadVoucher;
import cn.qingyumao.scaffold.repository.AggregateLoader;
import cn.qingyumao.scaffold.repository.AggregateRepository;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultCmdExecutor implements CmdExecutor<Object> {

    @Getter
    private final Class<?> cmdClazz;
    @Getter
    private final Class<? extends AggregateRoot<?>> aggregateRootClazz;


    private Map<Class<?>, AggregateLoader<?, ?>> aggregateLoader;

    private AggregateGenerator aggregateRootGenerator;

    private List<ObtainAggregateRootPostProcessor> aggregateRootPostProcessors;

    private List<BusinessExecutePostProcessor> businessExecutePostProcessors;

    private List<BusinessExecutor> businessExecutors;

    private AggregateRepository aggregateRepository;

    private EventDistributor distributor;

    public DefaultCmdExecutor(Class<?> cmdClazz, Class<? extends AggregateRoot<?>> aggregateRootClazz) {
        this.cmdClazz = cmdClazz;
        this.aggregateRootClazz = aggregateRootClazz;
    }


    @Override
    public void execute(Object cmd) {
        AggregateRoot ar = null;
        if (aggregateRootPostProcessors != null) {
            for (ObtainAggregateRootPostProcessor aggregateRootPostProcessor : aggregateRootPostProcessors) {
                ar = aggregateRootPostProcessor.beforeObtain(cmd);
                if (ar != null) {
                    return;
                }
            }
        }
        if (ar == null) {
            ar = obtainAggregateRoot(cmd);
        }
        if (ar == null) {
            throw new IllegalStateException("执行器获取领域聚合失败，领域对象不能为空");
        }
        if (aggregateRootPostProcessors != null) {
            for (ObtainAggregateRootPostProcessor aggregateRootPostProcessor : aggregateRootPostProcessors) {
                aggregateRootPostProcessor.afterObtain(cmd, ar);
            }
        }

        //
        executing(cmd, ar);

        aggregateRepository.save(ar);

        // distributor.distribute(ar.getEvents());
    }

    protected void executing(Object cmd, AggregateRoot ar) {
        if (!CollectionUtils.isEmpty(businessExecutePostProcessors)) {
            for (BusinessExecutePostProcessor postProcessor : businessExecutePostProcessors) {
                postProcessor.postProcessBeforeDoExecute(cmd, ar);
            }
        }
        if (!CollectionUtils.isEmpty(businessExecutors)) {
            for (BusinessExecutor businessExecutor : businessExecutors) {
                businessExecutor.doExecute(cmd, ar);
            }
        }
        if (!CollectionUtils.isEmpty(businessExecutePostProcessors)) {
            for (BusinessExecutePostProcessor postProcessor : businessExecutePostProcessors) {
                postProcessor.postProcessAfterDoExecute(cmd, ar);
            }
        }
    }


    protected AggregateRoot obtainAggregateRoot(Object cmd) {
        // 先尝试从数据库里获取
        AggregateRoot aggregateRoot = loadARFromStorage(cmd);
        // 尝试在命令中加载
        if (aggregateRootGenerator != null) {
            final AggregateRoot aggregateRootFromCmd = aggregateRootGenerator.generateAggregateRoot(cmd);
            if (aggregateRootFromCmd != null) {
                if (aggregateRoot != null) {
                    throw new IllegalStateException("命令中获取到了两个聚合对象，无法确定使用哪一个");
                }
                aggregateRoot = aggregateRootFromCmd;
            }
        }
        return aggregateRoot;
    }


    private AggregateRoot loadARFromStorage(Object cmd) {
        AggregateRoot aggregateRoot = null;
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
        final AggregateLoader loader = this.aggregateLoader.get(loadVoucherFieldType);
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

    public void setDistributor(EventDistributor distributor) {
        this.distributor = distributor;
    }

    public void setAggregateRepository(AggregateRepository aggregateRepository) {
        this.aggregateRepository = aggregateRepository;
    }

    public void setBusinessExecutors(List<BusinessExecutor> businessExecutors) {
        this.businessExecutors = businessExecutors;
    }

    public void setBusinessExecutePostProcessors(List<BusinessExecutePostProcessor> businessExecutePostProcessors) {
        this.businessExecutePostProcessors = businessExecutePostProcessors;
    }

    public void setAggregateRootPostProcessors(List<ObtainAggregateRootPostProcessor> aggregateRootPostProcessors) {
        this.aggregateRootPostProcessors = aggregateRootPostProcessors;
    }

    public void setAggregateRootGenerator(AggregateGenerator aggregateRootGenerator) {
        this.aggregateRootGenerator = aggregateRootGenerator;
    }

    public void setAggregateLoader(Map<Class<?>, AggregateLoader<?, ?>> aggregateLoader) {
        this.aggregateLoader = aggregateLoader;
    }
}
