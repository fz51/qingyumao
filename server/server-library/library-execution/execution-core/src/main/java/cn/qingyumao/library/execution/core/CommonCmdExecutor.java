package cn.qingyumao.library.execution.core;

import cn.qingyumao.library.domain.AggregateRoot;
import cn.qingyumao.library.domain.Id;
import cn.qingyumao.library.event.core.DomainEventPublisher;
import cn.qingyumao.library.event.core.DomainEventStorage;
import cn.qingyumao.library.domain.storage.AggregateDataExpirationException;
import cn.qingyumao.library.domain.storage.IAggregateRepository;
import cn.qingyumao.library.domain.storage.IAggregateTransactionManager;
import cn.qingyumao.library.domain.storage.LocalAggregateRepository;
import cn.qingyumao.library.extension.BeanContainerHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.locks.Lock;

/**
 * 命令执行器
 *
 * @author : fz51
 * @date : 2022/3/29 11:29 上午
 */
@Slf4j
public abstract class CommonCmdExecutor<C extends BaseCommand, AR extends AggregateRoot> {

    private IAggregateRepository aggregateRepository;


    protected abstract IAggregateRepository getAggregateRepository();

    private void initAggregateRepository() {
        aggregateRepository = getAggregateRepository();
        if (aggregateRepository == null) {
            // 设置默认内存存取
            aggregateRepository = new LocalAggregateRepository();
        }
    }

    public void execute(C cmd) {
        if (aggregateRepository == null) {
            initAggregateRepository();
        }
        final Optional<Lock> lock = getLock(cmd);
        if (lock.isPresent()) {
            lock.get().lock();
            try {
                doExecute(cmd);
            } finally {
                lock.get().unlock();
            }
        } else {
            doExecute(cmd);
        }

        completeExecution(cmd);
    }

    private void doExecute(C cmd) {
        // 获取聚合根对象
        AR ar = obtainAggregateAndExecute(cmd);
        final Optional<IAggregateTransactionManager> transactionManager = BeanContainerHolder.get().getAdaptiveBean(IAggregateTransactionManager.class);
        final Optional<DomainEventStorage> domainEventStorage = BeanContainerHolder.get().getAdaptiveBean(DomainEventStorage.class);

        try {
            transactionManager.ifPresent(IAggregateTransactionManager::begin);
            // 持久化。同时持久化事件
            aggregateRepository.save(ar);
            domainEventStorage.ifPresent(s -> s.save(ar.getEvents()));
            // 提交
            transactionManager.ifPresent(IAggregateTransactionManager::commit);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            try {
                transactionManager.ifPresent(IAggregateTransactionManager::rollback);
            } finally {
                // 删除持久话的事件
                domainEventStorage.ifPresent(s -> s.remove(ar.getEvents()));
            }
            throw e;
        }
        final Optional<DomainEventPublisher> domainEventPublisher = BeanContainerHolder.get().getAdaptiveBean(DomainEventPublisher.class);

        // 发布事件
        domainEventPublisher.ifPresent(p -> p.publishEvent(ar.getEvents()));
    }

    /**
     * 完成执行操作
     *
     */
    protected void completeExecution(C cmd) {

    }

    //////////////////////////////////////

    protected final AR obtainAggregateAndExecute(C cmd) {
        AR ar = null;
        final Optional<Id> uuId = cmd.getUUId();
        if (uuId.isPresent()) {
            // 从数据库加载
            Optional load = aggregateRepository.load(uuId.get());
            if (load.isPresent()) {
                ar = (AR) load.get();
                // 加载到
                postLoadedAggregate(cmd, ar);
                if (cmd.getDataVersion().isPresent()) {
                    // 如果这时加载的聚合版本号和命令获取的版本号不一致，数据已过期。
                    if (!cmd.getDataVersion().get().equals(ar.getDataVersion().get())) {
                        throw new AggregateDataExpirationException("聚合数据当前版本：" + ar.getDataVersion().get() + "，数据库版本：" + ((BaseCommand<?>) cmd).getDataVersion());
                    }
                }
            } else {
                notLoadAggregate(cmd);
            }
        }
        // 尝试在命令中获取
        if (ar == null) {
            ar = obtainAggregateFromCmd(cmd);
            if (ar != null) {
                ar.setDataVersion(0);
                ar.commitSnapshot();
            }
        }

        if (ar == null) {
            throw new IllegalStateException("获取领域对象失败，领域对象不能为空");
        }
        // 未执行任何作业之前，把领域对象数据备份一份
        ar.commitSnapshot();
        // 执行具体的任务信息
        doExecute(ar, cmd);
        return ar;
    }


    protected AR obtainAggregateFromCmd(C c) {
        return null;
    }

    /**
     * 已加载了聚合之后执行ø
     */
    protected void postLoadedAggregate(C cmd, AR ar) {

    }

    /**
     * 没有加载到聚合对象
     */
    protected void notLoadAggregate(C cmd) {

    }

    protected void doExecute(AR ar, C c) {

    }

    protected Optional<Lock> getLock(C cmd) {
        return Optional.empty();
    }

}
