package cn.qingyumao.library.extension;

/**
 * 容器
 *
 * @author fz51
 */
public class BeanContainerHolder {

    private static BeanContainer beanContainer;

    static {
        // todo init

    }

    public static BeanContainer get() {
        return BeanContainerHolder.beanContainer;
    }

    public static void set(BeanContainer beanContainer) {
        BeanContainerHolder.beanContainer = beanContainer;
    }
}
