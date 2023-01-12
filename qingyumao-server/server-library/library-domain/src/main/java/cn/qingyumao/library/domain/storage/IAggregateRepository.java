package cn.qingyumao.library.domain.storage;

import cn.qingyumao.library.domain.AggregateRoot;
import cn.qingyumao.library.domain.Id;

import java.util.Optional;

/**
 * 聚合服务接口。<br/>
 * 通过聚合服务，加载对应的聚合根实体。<br/>
 * 同时在进行逻辑处理之后，持久化聚合（聚合根）<br/>
 *
 * @author fz51
 */
public interface IAggregateRepository<AR extends AggregateRoot<ID>, ID extends Id> {

    /**
     * 加载聚合
     *
     * @param id
     * @return
     */
    Optional<AR> load(ID id);

    /**
     * 保存聚合
     *
     * @param ar
     * @throws AggregateSaveException
     */
    void save(AR ar) throws AggregateSaveException;

}