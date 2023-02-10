package cn.qingyumao.library.lock.autoconfig;

import cn.qingyumao.library.lock.core.SegmentTopicLock;
import cn.qingyumao.library.lock.core.TopicLock;
import cn.qingyumao.library.lock.core.WeakHashTopicLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(value = {LockProperties.class})
public class LockAutoConfiguration {

    /**
     * 默认添加 topicLock
     *
     * @param properties
     * @return
     */
    @ConditionalOnMissingBean(TopicLock.class)
    @Bean
    public TopicLock topicLock(LockProperties properties) {
        if ("hash".equals(properties.getTopicLockType())) {
            return new WeakHashTopicLock();
        } else if ("segment".equals(properties.getTopicLockType())) {
            return new SegmentTopicLock();
        }
        return new WeakHashTopicLock();
    }

}
