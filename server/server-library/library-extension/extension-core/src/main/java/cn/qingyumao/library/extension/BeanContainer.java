package cn.qingyumao.library.extension;

import java.util.Collection;
import java.util.Optional;

public interface BeanContainer {

    /**
     * 根据类获取对应的容器实例
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T> Optional<T> getAdaptiveBean(Class<T> clazz);

    /**
     * 获取实现接口I，并且定义T泛行的实例对象
     *
     * @param iClazz 接口类型
     * @param tClass 泛行类型
     * @param <I>
     * @param <T>
     * @return
     */
    <I, T> Optional<I> getAdaptiveBean(Class<I> iClazz, Class<T> tClass);
    /**
     * 获取实现接口I，并且定义T泛行的实例对象集合
     *
     * @param iClazz 接口类型
     * @param tClass 泛行类型
     * @param <I>
     * @param <T>
     * @return
     */
    <I, T> Collection<I> getAdaptiveBeans(Class<I> iClazz, Class<T> tClass);

}
