package cn.qingyumao.user.adapter.account.listener;

import cn.qingyumao.library.event.core.EventListener;
import cn.qingyumao.user.client.account.events.AccountRegisteredEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountRegisteredEventListener01 implements EventListener<AccountRegisteredEvent> {
    @Override
    public void onEvent(AccountRegisteredEvent event) {
        log.info("监听账户注册事件01");
    }
}
