package cn.qingyumao.library.domain;

/**
 * @author : fz51
 * @date : 2022/3/29 12:03 下午
 */

public interface IdGenerator<ID> {
    /**
     * 产生聚合根的唯一标识
     *
     * @return
     */
    ID generate();
}
