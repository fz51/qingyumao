package cn.qingyumao.user.client.account.events;

import cn.qingyumao.domain.ids.user.AccountId;
import cn.qingyumao.library.domain.event.DomainEvent;
import cn.qingyumao.library.domain.event.EventCreator;
import cn.qingyumao.library.domain.types.MobileNumber;
import cn.qingyumao.library.event.EventSource;
import lombok.Getter;

public class AccountContactMobileChangedEvent extends DomainEvent<AccountContactMobileChangedEvent.Source> {

    public AccountContactMobileChangedEvent(AccountId accountId, MobileNumber oldContactMobile, MobileNumber newContactMobile
    ,EventCreator creator) {
        super(new Source(accountId, newContactMobile, oldContactMobile),creator);
    }


    static final class Source extends EventSource {
        @Getter
        private AccountId accountId;
        /**
         * 废弃联系手机号
         */
        @Getter
        private MobileNumber oldContactMobile;

        /**
         * 新手机号
         */
        @Getter
        private MobileNumber newContactMobile;

        public Source(AccountId accountId, MobileNumber newContactMobile, MobileNumber oldContactMobile) {
            this.accountId = accountId;
            this.oldContactMobile = oldContactMobile;
            this.newContactMobile = newContactMobile;
        }
    }
}
