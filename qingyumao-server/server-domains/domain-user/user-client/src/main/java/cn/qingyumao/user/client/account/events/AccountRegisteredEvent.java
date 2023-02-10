package cn.qingyumao.user.client.account.events;

import cn.qingyumao.domain.ids.user.AccountId;
import cn.qingyumao.library.domain.types.MobileNumber;
import cn.qingyumao.library.event.core.AbsDomainEvent;
import cn.qingyumao.library.event.core.EventCreator;
import cn.qingyumao.library.event.core.EventSource;
import lombok.Getter;

/**
 * 账户已注册事件
 */
public class AccountRegisteredEvent extends AbsDomainEvent<AccountRegisteredEvent.Source> {

    public AccountRegisteredEvent(AccountId accountId, String uniqueCode, MobileNumber contactMobile
            , EventCreator creator) {
        super(new Source(accountId, uniqueCode, contactMobile), creator);
    }


    static final class Source extends EventSource {
        @Getter
        private AccountId accountId;

        /**
         * 工位号
         */
        @Getter
        private String uniqueCode;

        /**
         * 手机号
         */
        @Getter
        private MobileNumber contactMobile;

        public Source(AccountId accountId, String uniqueCode, MobileNumber contactMobile) {
            this.accountId = accountId;
            this.uniqueCode = uniqueCode;
            this.contactMobile = contactMobile;
        }
    }
}
