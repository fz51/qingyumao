package cn.qingyumao.library.execution;

public class CommandResp<D> {

    private D data;


    private boolean success;

    private CommandResp(D data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public static <R> CommandResp<R> success(R data) {
        final CommandResp resp = new CommandResp<>(data, true);
        return resp;

    }
}
