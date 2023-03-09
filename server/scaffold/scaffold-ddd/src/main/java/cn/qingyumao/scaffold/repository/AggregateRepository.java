package cn.qingyumao.scaffold.repository;

import cn.qingyumao.scaffold.domain.AggregateRoot;

/**
 * 聚合服务接口。<br/>
 * 通过聚合服务，加载对应的聚合根实体。<br/>
 * 同时在进行逻辑处理之后，持久化聚合（聚合根）<br/>
 *
 * @author fz51
 */
public interface AggregateRepository<AR extends AggregateRoot<?>> {

    /**
     * 保存聚合
     *
     * @param ar
     * @throws
     */
    void save(AR ar);
}