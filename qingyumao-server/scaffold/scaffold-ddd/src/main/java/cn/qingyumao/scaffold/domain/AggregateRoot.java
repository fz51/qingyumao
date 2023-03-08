package cn.qingyumao.scaffold.domain;


import cn.hutool.core.util.ObjectUtil;
import cn.qingyumao.scaffold.domain.event.AbstractDomainEvent;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 聚合根
 *
 * @param <ID>
 * @author fz51
 */
public abstract class AggregateRoot<ID extends Id> implements Serializable {

    private final ID id;
    /**
     * 聚合来源
     */
    @Getter
    private final EntitySource source;

    /**
     * 删除状态状态
     */
    private boolean deleted = false;


    /**
     *
     */
    private transient List<AbstractDomainEvent> events = new ArrayList<>();


    /**
     * 快照
     */
    private transient List<AggregateRoot> snapshots = new ArrayList<>();

    public AggregateRoot(ID id, EntitySource source) {
        if (id == null) {
            throw new IllegalStateException("aggregate Root id must not null");
        }
        this.id = id;
        this.source = source;
    }

    public ID getId() {
        return id;
    }

    private transient Optional<Integer> dataVersion = Optional.empty();


    public void delete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return this.deleted;
    }


    /**
     * 添加领域事件。<br/>
     * 再一次命令触发过程中，不能发生相同的事件。如果需要重复发生事件。请自定义事件的次数
     *
     * @param event
     */
    public void addEvent(AbstractDomainEvent event) {
        this.events.add(event);
    }

    public List<AbstractDomainEvent> getEvents() {
        return this.events;
    }


    public Optional<Integer> getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Optional<Integer> dataVersion) {
        this.dataVersion = dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = Optional.ofNullable(dataVersion);
    }


    public Optional<AggregateRoot> getLastSnapshot() {
        if (snapshots.size() > 0) {
            return Optional.of(snapshots.get(snapshots.size() - 1));
        }
        return Optional.empty();
    }

    public void commitSnapshot() {
        snapshots.add(ObjectUtil.clone(this));
    }


}
