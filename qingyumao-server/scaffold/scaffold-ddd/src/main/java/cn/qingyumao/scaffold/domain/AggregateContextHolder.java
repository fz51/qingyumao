package cn.qingyumao.scaffold.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AggregateContextHolder {

    private final static Map<Id, AggregateContext> CONTEXTS = new ConcurrentHashMap<>();

    public static AggregateContext get(Id aggregateId) {
        final AggregateContext aggregateContext = CONTEXTS.get(aggregateId);
        if (aggregateContext == null) {
            return CONTEXTS.put(aggregateId, new DefaultAggregateContext());
        }
        return aggregateContext;
    }

}
