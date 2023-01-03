package cn.qingyumao.library.execution;

import cn.qingyumao.library.domain.AggregateRoot;
import cn.qingyumao.library.domain.event.DomainEventPublisher;
import cn.qingyumao.library.domain.storage.AggregateDataExpirationException;
import cn.qingyumao.library.domain.storage.IAggregateRepository;
import cn.qingyumao.library.domain.storage.IAggregateTransactionManager;
import cn.qingyumao.library.domain.storage.LocalAggregateRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 命令执行器
 *
 * @author : fz51
 * @date : 2022/3/29 11:29 上午
 */
@Slf4j
public abstract class CommonCmdExecutor<C extends BaseCommand, AR extends AggregateRoot> {

    private DomainEventPublisher domainEventPublisher;

    public void setDomainEventPublisher(DomainEventPublisher eventPublisher) {
        this.domainEventPublisher = eventPublisher;
    }

    private IAggregateRepository aggregateRepository;


    private IAggregateTransactionManager aggregateTransactionManager;

    protected abstract IAggregateRepository getAggregateRepository();

    public void execute(C cmd) {
        if (aggregateRepository == null) {
            aggregateRepository = getAggregateRepository();
        }
        if (aggregateRepository == null) {
            // 本地存取
            aggregateRepository = new LocalAggregateRepository();
        }
        // 获取聚合根对象
        AR ar = obtainAggregateAndExecute(cmd);
        try {
            aggregateTransactionManager.begin();
            // 持久化。同时持久化事件
            aggregateRepository.save(ar);
            // 发布事件
            domainEventPublisher.publishEvent(ar.getEvents());
            // 提交
            aggregateTransactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            aggregateTransactionManager.rollback();
            throw e;
        }
        completeExecution(cmd);
    }

    /**
     * 完成执行操作
     *
     * @param cmd
     */
    protected void completeExecution(C cmd) {

    }

    //////////////////////////////////////

    protected final AR obtainAggregateAndExecute(C cmd) {
        AR ar = obtainAggregateFromCmd(cmd);
        if (ar != null) {
            ar.setDataVersion(0);
            ar.commitSnapshot();
        }
        if (ar == null) {
            if (cmd.getUUId().isPresent()) {
                // 从数据库加载
                Optional load = aggregateRepository.load(cmd.getUUId().get());
                if (load.isPresent()) {
                    ar = (AR) load.get();
                    if (cmd.getDataVersion().isPresent()) {
                        // 如果这时加载的聚合版本号和命令获取的版本号不一致，数据已过期。
                        if (!cmd.getDataVersion().get().equals(ar.getDataVersion().get())) {
                            throw new AggregateDataExpirationException("聚合数据当前版本：" + ar.getDataVersion().get() + "，数据库版本：" + ((BaseCommand<?>) cmd).getDataVersion());
                        }
                    }

                }
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

    protected void doExecute(AR ar, C c) {

    }

    public void setAggregateTransactionManager(IAggregateTransactionManager aggregateTransactionManager) {
        this.aggregateTransactionManager = aggregateTransactionManager;
    }
}
