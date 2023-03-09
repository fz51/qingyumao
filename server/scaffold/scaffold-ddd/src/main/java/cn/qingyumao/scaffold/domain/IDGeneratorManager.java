package cn.qingyumao.scaffold.domain;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class IDGeneratorManager {

    private static final Map<Class<?>, IdGenerator<?>> MAP = new HashMap<>();

    public static <ID extends Id> IdGenerator<ID> getIdGenerator(Class<ID> idClazz) {

        final IdGenerator<?> idGenerator = MAP.get(idClazz);
        if (idGenerator == null) {
            MAP.put(idClazz, new DefaultGenerator<>(idClazz));
        }
        return (IdGenerator<ID>) idGenerator;
    }

    static class DefaultGenerator<T extends Id> implements IdGenerator<T> {

        private final Class<T> idClazz;

        DefaultGenerator(Class<T> idClazz) {
            this.idClazz = idClazz;
        }

        @Override
        public T generate() {
            final Constructor<T> constructor = ReflectUtil.getConstructor(idClazz, Long.class);
            if (constructor != null) {
                final long id = IdUtil.getSnowflake().nextId();
                return ReflectUtil.newInstance(idClazz, id);
            }

            return null;

        }
    }
}
