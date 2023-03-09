package cn.qingyumao.scaffold.repository;

import cn.qingyumao.scaffold.domain.AggregateRoot;
import cn.qingyumao.scaffold.execution.AggregateRootMatcher;

/**
 * 聚合服务接口。<br/>
 * 通过聚合服务，加载对应的聚合根实体。<br/>
 * 同时在进行逻辑处理之后，持久化聚合（聚合根）<br/>
 *
 * @author fz51
 */
public interface AggregateLoader<BUID,AR extends AggregateRoot<?>> extends AggregateRootMatcher {

    /**
     * 保存聚合
     *
     * @param
     * @throws
     */
    AR load(BUID id);

}