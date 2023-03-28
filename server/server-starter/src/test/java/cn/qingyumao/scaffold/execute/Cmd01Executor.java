package cn.qingyumao.scaffold.execute;

import cn.qingyumao.scaffold.ddd.execution.CmdExecutor;
import lombok.extern.slf4j.Slf4j;

// @Component
@Slf4j
public class Cmd01Executor implements CmdExecutor<Cmd01> {
    @Override
    public void execute(Cmd01 cmd) {
        log.info("自定义执行器");
    }
}
