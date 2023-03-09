package cn.qingyumao.user.domain.account;

import cn.qingyumao.domain.ids.user.AccountId;
import cn.qingyumao.library.domain.Id;
import cn.qingyumao.user.domain.account.model.AccountUniqueCode;
import lombok.Getter;

public class AccountUUID implements Id {

    @Getter
    private AccountId id;

    @Getter
    private AccountUniqueCode uniqueCode;

    private AccountUUID(AccountId id, AccountUniqueCode uniqueCode) {
        if (id == null && uniqueCode == null) {
            throw new IllegalStateException("AccountUUID 创建失败");
        }
        this.id = id;
        this.uniqueCode = uniqueCode;
    }

    public static AccountUUID of(AccountId id) {
        return new AccountUUID(id, null);
    }

    public static AccountUUID of(AccountUniqueCode uniqueCode) {
        return new AccountUUID(null, uniqueCode);
    }

}
