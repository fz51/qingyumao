package cn.qingyumao.library.lock.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "qingyumao.library.lock")
@Data
public class LockProperties {
    /**
     * 主题锁类型：hash
     * segment
     */
    private String topicLockType = "hash";

}
