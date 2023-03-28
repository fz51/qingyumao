package cn.qingyumao.scaffold.ddd.domain;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class IDGeneratorHolder {

    private static final Map<Class<?>, IdGenerator<?>> MAP = new HashMap<>();

    public static <ID> IdGenerator<ID> getIdGenerator(Class<ID> idClazz) {

        IdGenerator<ID> idGenerator = (IdGenerator) MAP.get(idClazz);
        if (idGenerator == null) {
            idGenerator = new DefaultGenerator<>(idClazz);
            MAP.put(idClazz, idGenerator);
        }
        return idGenerator;
    }

    public static void setIdGenerator(IdGenerator<?> idGenerator) {
        Class<?> idClass = Object.class;
        if (idGenerator instanceof IdGeneratorMethodAdapter) {
            idClass = ((IdGeneratorMethodAdapter) idGenerator).getIdGeneratorGenericClass();
        } else {
            final Class<?>[] idClasses = GenericTypeResolver.resolveTypeArguments(idGenerator.getClass(), IdGenerator.class);
            if (idClasses != null) {
                idClass = idClasses[0];
            }
        }
        final IdGenerator<?> existed = MAP.get(idClass);
        if (existed == null) {
            MAP.put(idClass, idGenerator);
        } else {
            throw new IllegalStateException(idClass.getName() + " id生成器存在多个");
        }
    }

    static class DefaultGenerator<T> implements IdGenerator<T> {

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
