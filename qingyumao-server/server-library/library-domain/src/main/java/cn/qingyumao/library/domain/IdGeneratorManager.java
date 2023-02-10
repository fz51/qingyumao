package cn.qingyumao.library.domain;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.qingyumao.library.extension.BeanContainerHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
        final Optional<IdGenerator> generatorOptional = BeanContainerHolder.get().getAdaptiveBean(IdGenerator.class, idClazz);
        if (generatorOptional.isPresent()) {
            ID_GENERATOR_MAP.put(idClazz, generatorOptional.get());
        } else {
            // 设置默认id 生成器
            if (StrId.class.isAssignableFrom(idClazz)) {
                ID_GENERATOR_MAP.put(idClazz, new DefaultStringIdGenerator(idClazz));
            } else if (LongId.class.isAssignableFrom(idClazz)) {
                ID_GENERATOR_MAP.put(idClazz, new DefaultLongIdGenerator(idClazz));
            }
        }
        if (ID_GENERATOR_MAP.get(idClazz) == null) {
            log.error(idClazz.getName() + " not has IdGenerator");
            throw new IllegalStateException(idClazz.getName() + " not has IdGenerator");
        }
        return (ID) ID_GENERATOR_MAP.get(idClazz).generate();
    }

    static class DefaultStringIdGenerator<T> implements IdGenerator {

        private Class clazz;

        public DefaultStringIdGenerator(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public T generate() {
            final T t = (T) ReflectUtil.newInstance(clazz, UUID.randomUUID().toString());
            return t;
        }
    }

    static class DefaultLongIdGenerator<T> implements IdGenerator {

        private Class clazz;

        public DefaultLongIdGenerator(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public T generate() {
            final T t = (T) ReflectUtil.newInstance(clazz, IdUtil.getSnowflake().nextId());
            return t;
        }
    }
}
