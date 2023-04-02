package cn.qingyumao.scaffold.web;

import cn.qingyumao.scaffold.ddd.execution.CmdDispatcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用
 */
public class CommonController {

    private CmdDispatcher cmdDispatcher;


    protected void handleCmd(Object cmd) {
        cmdDispatcher.dispatch(cmd);
    }

    @Autowired
    public void setCmdDispatcher(CmdDispatcher cmdDispatcher) {
        this.cmdDispatcher = cmdDispatcher;
    }
}
