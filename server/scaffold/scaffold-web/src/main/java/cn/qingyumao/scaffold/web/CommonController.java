package cn.qingyumao.scaffold.web;

import cn.qingyumao.scaffold.execution.CmdDispatcher;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class CommonController implements SmartInitializingSingleton {

    private CmdDispatcher cmdDispatcher;

    public void handleCmd(Object cmd) {
        cmdDispatcher.dispatch(cmd);
    }

    @Override
    public void afterSingletonsInstantiated() {
        Assert.notNull(cmdDispatcher, "cmdDispatcher is not null");
    }


    public void setCmdDispatcher(@Autowired CmdDispatcher cmdDispatcher) {
        this.cmdDispatcher = cmdDispatcher;
    }
}
