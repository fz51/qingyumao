package cn.qingyumao.scaffold.execute;

import cn.qingyumao.scaffold.ddd.repository.AggregateCommit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Cmd01AggregateRepository implements AggregateCommit<Cmd01AggregateRoot> {

    @Override
    public void commit(Cmd01AggregateRoot cmd01AggregateRoot) {
        log.info("保存 cmd01AggregateRoot:{}", cmd01AggregateRoot.getId());
    }
}
