package cn.qingyumao.library.execution;

import java.util.Optional;

/**
 * @author : fz51
 * @date : 2022/3/29 12:08 下午
 */
public class BaseCommand<R> {

    /**
     * 处理命令后产生的响应
     */
    private CommandResp<R> cmdResponse;

    public void setCmdResponse(R response) {
        this.cmdResponse = CommandResp.success(response);
    }

    public CommandResp<R> getCmdResponse() {
        return this.cmdResponse;
    }

    /**
     * uuId
     *
     * @return
     */
    public Optional<?> getUUId() {
        return Optional.empty();
    }

    /**
     * 获取编辑时的一个数据版本好
     *
     * @return
     */
    public Optional<Integer> getDataVersion() {
        return Optional.empty();
    }

}
