package cn.qingyumao.library.lock.core;

import java.util.concurrent.locks.Lock;

public interface TopicLock<T> {

    Lock getLock(T topic);
}
