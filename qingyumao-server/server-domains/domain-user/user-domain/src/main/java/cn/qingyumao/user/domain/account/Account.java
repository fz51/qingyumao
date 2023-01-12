package cn.qingyumao.user.domain.account;

import cn.qingyumao.domain.ids.user.AccountId;
import cn.qingyumao.library.domain.AggregateRoot;
import cn.qingyumao.library.domain.EntitySource;
import cn.qingyumao.library.domain.IdGeneratorManager;
import cn.qingyumao.library.domain.event.EventCreator;
import cn.qingyumao.library.domain.types.MobileNumber;
import cn.qingyumao.user.client.account.events.AccountContactMobileChangedEvent;
import cn.qingyumao.user.domain.account.model.AccountDescription;
import cn.qingyumao.user.domain.account.model.AccountUniqueCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用用户账户。
 *
 * @author : fz51
 * @date : 2022/2/28 10:52 上午
 */
@ToString
@Slf4j
public class Account extends AggregateRoot<AccountId> {

    /**
     * 账户唯一值。可以是工号
     */
    @Getter
    private final AccountUniqueCode uniqueCode;

    /**
     * 联系手机号
     */
    @Getter
    private MobileNumber contactMobile;

    @Getter
    private Boolean enabled;

    /**
     * 描述
     */
    @Getter
    private AccountDescription description;


    private Account(AccountId id, EntitySource source, AccountUniqueCode uniqueCode) {
        super(id, source);
        this.uniqueCode = uniqueCode;
    }

    public static Account create(AccountUniqueCode uniqueCode,
                                 MobileNumber contactMobile,
                                 Boolean enabled,
                                 AccountDescription description) {
        Account account = new Account(IdGeneratorManager.generate(AccountId.class), EntitySource.ORIGIN, uniqueCode);
        account.contactMobile = contactMobile;
        account.description = description;
        account.enabled = enabled;
        account.setDataVersion(0);
        return account;
    }

    public static Account load(AccountId id, Integer version,
                               AccountUniqueCode uniqueCode, MobileNumber contactMobile,
                               Boolean enabled,
                               AccountDescription description
    ) {
        Account account = new Account(id, EntitySource.REPOSITORY_LOAD, uniqueCode);
        account.contactMobile = contactMobile;
        account.description = description;
        account.enabled = enabled;
        account.setDataVersion(version);
        return account;
    }

    /**
     * 修改备注描述
     *
     * @param description
     */
    public void changeDescription(AccountDescription description) {
        this.description = description;
    }

    /**
     * 更改联系手机号码
     *
     * @param newContactMobile
     */
    public void changeContactMobile(MobileNumber newContactMobile) {
        if (!this.contactMobile.equals(newContactMobile)) {
            // 禁用之前开通的手机号登录
            MobileNumber oldContactMobile = this.contactMobile;
            addEvent(new AccountContactMobileChangedEvent(this.getId(), oldContactMobile, newContactMobile, new EventCreator("None")));
            this.contactMobile = newContactMobile;
        }
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }
}
