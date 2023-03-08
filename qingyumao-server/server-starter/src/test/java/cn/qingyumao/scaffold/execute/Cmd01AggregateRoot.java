package cn.qingyumao.scaffold.execute;

import cn.qingyumao.scaffold.domain.AggregateRoot;
import cn.qingyumao.scaffold.domain.EntitySource;

public class Cmd01AggregateRoot extends AggregateRoot<Cmd01AggregateId> {

    public Cmd01AggregateRoot(Cmd01AggregateId id, EntitySource source) {
        super(id, source);
    }
}
