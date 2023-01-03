package cn.qingyumao.library.execution;

import cn.qingyumao.library.domain.AggregateRoot;
import cn.qingyumao.library.domain.event.DomainEventPublisher;
import cn.qingyumao.library.domain.storage.IAggregateRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 命令执行器
 *
 * @author : fz51
 * @date : 2022/3/29 11:29 上午
 */
@Slf4j
public abstract class AbstractCmdExecutor<C extends BaseCommand, AR extends AggregateRoot> {
    /**
     * 持久化
     */
    private boolean persistence = true;

    private DomainEventPublisher domainEventPublisher;



    public void setDomainEventPublisher(DomainEventPublisher eventPublisher) {
        this.domainEventPublisher = eventPublisher;
    }

    public abstract IAggregateRepository getAggregateService();


    public void execute(C cmd) {
        try {
            // 获取聚合根对象
            final AggregateRoot ar = obtainAggregateAndExecute(cmd);
            // 持久化。同时持久化事件
            if (isPersistence()) {
                // 判断是否有对象修改
                getAggregateService().save(ar);
            }
            // 发布事件
            domainEventPublisher.publishEvent(ar.getEvents());
            // 记录aggressive 的日志

            //
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw e;
        }
        // this.commands.remove(cmd);
        completeExecution(cmd);
    }

    /**
     * 完成执行操作
     *
     * @param cmd
     */
    protected void completeExecution(C cmd) {

    }


    /**
     * 获取聚合根，并执行业务方法。
     *
     * @return
     */
    protected abstract AR obtainAggregateAndExecute(C cmd);

    //////////////////////////////////////

    /**
     * 是否需要持久化
     *
     * @return
     */
    public boolean isPersistence() {
        return this.persistence;
    }

    public void setPersistence(boolean persistence) {
        this.persistence = persistence;
    }
}
