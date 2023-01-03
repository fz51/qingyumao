package cn.qingyumao.library.domain.storage;

/**
 * 聚合数据过期异常
 *
 * @author : fz51
 * @date : 2022/2/16 10:14 上午
 */
public class AggregateDataExpirationException extends AggregateSaveException {

    public AggregateDataExpirationException(String errorMessage) {
        super(errorMessage);
    }

}
