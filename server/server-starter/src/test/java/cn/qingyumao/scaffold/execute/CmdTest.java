package cn.qingyumao.scaffold.execute;

import cn.qingyumao.scaffold.execution.CmdDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class CmdTest {

    @Autowired
    private CmdDispatcher cmdDispatcher;

    @Test
    public void test() {
        final Cmd01 cmd01 = new Cmd01();
        cmdDispatcher.dispatch(cmd01);
    }


}
