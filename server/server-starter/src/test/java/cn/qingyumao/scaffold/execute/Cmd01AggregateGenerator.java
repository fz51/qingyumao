package cn.qingyumao.scaffold.execute;

import cn.qingyumao.scaffold.ddd.domain.EntitySource;
import cn.qingyumao.scaffold.ddd.execution.AggregateGenerator;
import org.springframework.stereotype.Component;

@Component
public class Cmd01AggregateGenerator implements AggregateGenerator<Cmd01, Cmd01AggregateRoot> {
    @Override
    public Cmd01AggregateRoot generateAggregateRoot(Cmd01 cmd) {
        return new Cmd01AggregateRoot(new Cmd01AggregateId(), EntitySource.ORIGIN);
    }
}
