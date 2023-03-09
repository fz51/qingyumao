package cn.qingyumao.library.domain;

/**
 * @author : fz51
 * @date : 2021/12/24 9:59 上午
 */
public class LongId implements Id {

    private Long val;

    public LongId(Long value) {
        this.val = value;
        if (value.longValue() < 0) {
            throw new IllegalArgumentException("long id must not less than 0");
        }
    }

    public Long getValue() {
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
