package cn.qingyumao.user.app.account.executor;

import cn.qingyumao.library.domain.Id;
import cn.qingyumao.library.domain.types.MobileNumber;
import cn.qingyumao.library.execution.core.BaseCommand;
import cn.qingyumao.user.domain.account.AccountUUID;
import cn.qingyumao.user.domain.account.model.AccountDescription;
import cn.qingyumao.user.domain.account.model.AccountName;
import cn.qingyumao.user.domain.account.model.AccountUniqueCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;


/**
 * 账户创建命令
 *
 * @author : fz51
 * @date : 2022/2/28 1:31 下午
 */
@Getter
@Setter
public class AccountCreateCmd extends BaseCommand {

    private AccountName name;

    private MobileNumber contactMobile;
    /**
     * 是否开通手机号登录
     */
    private Boolean enabledContactMobileLogin;

    /**
     *
     */
    private Boolean enabled;

    private AccountUniqueCode uniqueCode;


    private AccountDescription description;

    @Override
    public Optional<Id> getUUId() {
        return Optional.of(AccountUUID.of(this.uniqueCode));
    }
}
