package cn.qingyumao.library.domain.storage;

/**
 * 聚合存储异常
 *
 * @author : fz51
 * @date : 2022/2/16 10:14 上午
 */
public class AggregateSaveException extends RuntimeException {

    public AggregateSaveException(String errorMessage) {
        super(errorMessage);
    }

}
