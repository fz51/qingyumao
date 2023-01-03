package cn.qingyumao.library.extension;

public interface BeanContainer {

    /**
     * 根据类获取对应的容器实例
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getAdaptiveBean(Class<T> clazz);

    /**
     * 获取接口类型
     *
     * @param iClazz 接口类型
     * @param tClass 泛行类型
     * @param <I>
     * @param <T>
     * @return
     */
    <I, T> I getAdaptiveBean(Class<I> iClazz, Class<T> tClass);

}
