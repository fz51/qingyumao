package cn.qingyumao.scaffold.common;

import org.springframework.context.ApplicationContext;

/**
 * spring 工具类
 */
public class SpringUtil {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }
}
