package cn.qingyumao.test.library;

import cn.qingyumao.library.lock.core.TopicLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.Lock;


@SpringBootTest
@Slf4j
public class TopicLockTest {

    @Autowired
    private TopicLock topicLock;

    @Test
    public void autoTopicLock() {

        final Lock lock = topicLock.getLock("123");
        log.warn("===" + lock.getClass().getName());
    }




}
