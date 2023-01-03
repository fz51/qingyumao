package cn.qingyumao.library.domain.storage;

import cn.qingyumao.library.domain.AggregateRoot;
import cn.qingyumao.library.domain.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LocalAggregateRepository implements IAggregateRepository<AggregateRoot<Id>, Id> {

    private Map<Id, AggregateRoot> localStorage = new HashMap<>();

    @Override
    public Optional<AggregateRoot<Id>> load(Id id) {
        return Optional.ofNullable(localStorage.get(id));
    }

    @Override
    public void save(AggregateRoot<Id> aggregateRoot) throws AggregateSaveException {
        localStorage.put(aggregateRoot.getId(), aggregateRoot);
    }
}
