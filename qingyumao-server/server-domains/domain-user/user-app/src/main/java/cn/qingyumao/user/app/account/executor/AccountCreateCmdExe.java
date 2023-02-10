package cn.qingyumao.user.app.account.executor;

import cn.qingyumao.library.domain.storage.IAggregateRepository;
import cn.qingyumao.library.execution.core.CommonCmdExecutor;
import cn.qingyumao.library.lock.core.TopicLock;
import cn.qingyumao.user.domain.account.Account;
import cn.qingyumao.user.domain.account.IAccountRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.locks.Lock;

/**
 * @author : fz51
 * @date : 2022/2/28 1:31 下午
 */
@Slf4j
public class AccountCreateCmdExe extends CommonCmdExecutor<AccountCreateCmd, Account> {

    @Resource
    private IAccountRepository accountRepository;

    @Resource
    private TopicLock topicLock;

    @Override
    protected void postLoadedAggregate(AccountCreateCmd cmd, Account account) {
        log.info("工号" + cmd.getUniqueCode().getValue() + "已存在");
        throw new IllegalArgumentException("工号" + cmd.getUniqueCode().getValue() + "已存在");
    }

    @Override
    protected Account obtainAggregateFromCmd(AccountCreateCmd cmd) {
        Account account = Account.create(cmd.getUniqueCode(), cmd.getContactMobile(), cmd.getEnabled(), cmd.getDescription());
        return account;
    }

    @Override
    protected IAggregateRepository getAggregateRepository() {
        return this.accountRepository;
    }


    @Override
    protected Optional<Lock> getLock(AccountCreateCmd cmd) {
        return Optional.of(topicLock.getLock("account_unique_code"));
    }
}
