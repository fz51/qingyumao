package cn.qingyumao.test.lock;

import cn.qingyumao.library.lock.core.SegmentTopicLock;
import cn.qingyumao.library.lock.core.TopicLock;
import cn.qingyumao.library.lock.core.WeakHashTopicLock;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Slf4j
public class TopicLockTest {

    public static void main(String[] args) throws Exception {
        final TopicLock topicLock = new WeakHashTopicLock();
        final Lock lock = topicLock.getLock("你好啊");
        test(topicLock);
        System.gc();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("---------");

        test(topicLock);
        lock.lock();
        log.info("lock lock");
        lock.unlock();
    }

    private static void test(TopicLock topicLock){
        List list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            var ce = topicLock.getLock("123" + i % 12);
            list.add(ce);
        }
    }

    public static void main1(String[] args) throws Exception {
        final SegmentTopicLock topicLock = new SegmentTopicLock();
        final Thread t1 = new Thread(() -> {
            final Lock lock1 = topicLock.getLock("123");
            lock1.lock();
            log.info("lock1 get lock");
            sleep();
            lock1.unlock();
            log.info("lock1 unlock");
        });
        t1.start();
        final Thread t2 = new Thread(() -> {
            final Lock lock1 = topicLock.getLock("1234");
            lock1.lock();
            log.info("lock get lock");
            sleep();
            lock1.unlock();
            log.info("lock1 unlock");
        });
        t2.start();
        t1.join();
        t2.join();
        System.out.println(topicLock.getLock("123"));
        System.out.println(topicLock.getLock("123"));
        System.out.println(topicLock.getLock("123"));
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
