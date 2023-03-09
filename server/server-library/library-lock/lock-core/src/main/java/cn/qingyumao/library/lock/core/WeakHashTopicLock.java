package cn.qingyumao.library.lock.core;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 采用弱引用，每个hash 获取对应的锁。当发生 gc 的时候，会清除不被引用的锁。
 */
@Slf4j
public class WeakHashTopicLock implements TopicLock {

    private ConcurrentHashMap<Object, WeakLockRef<Object, ReentrantLock>> lockMap = new ConcurrentHashMap<>();
    private ReferenceQueue<ReentrantLock> queue = new ReferenceQueue<>();

    /**
     * 清除空引用锁
     */
    private void clearEmptyRef() {
        Reference<? extends ReentrantLock> ref;
        while ((ref = queue.poll()) != null) {
            WeakLockRef<Object, ? extends ReentrantLock> weakLockRef = (WeakLockRef<Object, ? extends ReentrantLock>) ref;
            lockMap.remove(weakLockRef.key);
        }
    }

    @Override
    public Lock getLock(Object topic) {

        if (lockMap.size() > 10) {
            log.warn("清除前锁数量：{}", lockMap.size());
            clearEmptyRef();
            log.warn("清除后锁数量：{}", lockMap.size());
        }
        WeakReference<ReentrantLock> lockRef = lockMap.get(topic);
        ReentrantLock lock = (lockRef == null ? null : lockRef.get());
        while (lock == null) {
            lockMap.putIfAbsent(topic, new WeakLockRef<>(new ReentrantLock(), queue, topic));
            lockRef = lockMap.get(topic);
            lock = (lockRef == null ? null : lockRef.get());
            if (lock != null) {
                return lock;
            }
            clearEmptyRef();
        }
        return lock;
    }

    private static final class WeakLockRef<T, K> extends WeakReference<K> {
        final T key;

        private WeakLockRef(K referent, ReferenceQueue<? super K> q, T key) {
            super(referent, q);
            this.key = key;
        }
    }

}
