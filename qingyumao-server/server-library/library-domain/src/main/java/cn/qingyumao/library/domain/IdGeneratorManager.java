package cn.qingyumao.library.domain;

import cn.hutool.core.util.IdUtil;
import cn.qingyumao.library.extension.BeanContainerHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author fz51
 */
@Slf4j
public class IdGeneratorManager {

    /**
     * 收集ID生成器
     */
    private static Map<Class<?>, IdGenerator> ID_GENERATOR_MAP;

    private static IdGenerator<StrId> DEFAULT_STRING_ID_GENERATOR = new DefaultStringIdGenerator();

    private static IdGenerator<LongId> DEFAULT_LONG_ID_GENERATOR = new DefaultLongIdGenerator();

    static {
        init();
    }

    private static void init() {
        ID_GENERATOR_MAP = new HashMap<>();
        // todo
    }

    public static <ID extends Id> ID generate(Class<ID> idClazz) {

        IdGenerator<ID> generator = ID_GENERATOR_MAP.get(idClazz);
        if (generator != null) {
            return generator.generate();
        }
        // 尝试从容器中获取
        generator = BeanContainerHolder.get().getAdaptiveBean(IdGenerator.class, idClazz);
        if (generator != null) {
            ID_GENERATOR_MAP.put(idClazz, generator);
        } else {
            // 设置默认id 生成器
            if (StrId.class.isAssignableFrom(idClazz)) {
                ID_GENERATOR_MAP.put(idClazz, DEFAULT_STRING_ID_GENERATOR);
            } else if (LongId.class.isAssignableFrom(idClazz)) {
                ID_GENERATOR_MAP.put(idClazz, DEFAULT_LONG_ID_GENERATOR);
            }
        }
        if (ID_GENERATOR_MAP.get(idClazz) == null) {
            log.error(idClazz.getName() + " not has IdGenerator");
            throw new IllegalStateException(idClazz.getName() + " not has IdGenerator");
        }
        return (ID) ID_GENERATOR_MAP.get(idClazz).generate();
    }

    static class DefaultStringIdGenerator implements IdGenerator<StrId> {
        @Override
        public StrId generate() {
            return new StrId(UUID.randomUUID().toString());
        }
    }

    static class DefaultLongIdGenerator implements IdGenerator<LongId> {
        @Override
        public LongId generate() {
            return new LongId(IdUtil.getSnowflake().nextId());
        }
    }
}
