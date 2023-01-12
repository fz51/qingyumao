package cn.qingyumao.domain.ids.user;

import cn.qingyumao.library.domain.LongId;

public class AccountId extends LongId {

    private AccountId(Long value) {
        super(value);
    }

    public static AccountId of(Long value) {
        return new AccountId(value);
    }
}
