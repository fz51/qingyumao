package cn.qingyumao.scaffold.repository;

import cn.qingyumao.scaffold.domain.AggregateRoot;

import java.util.HashMap;
import java.util.Map;

public class AggregateLoaderManager {

    private Map<Class<? extends AggregateRoot>, Map<Class, AggregateLoader>> cashed = new HashMap<>();

    public AggregateLoader get(Class aggregateClazz, Object businessId) {

        return cashed.get(aggregateClazz).get(businessId.getClass());
    }

}
