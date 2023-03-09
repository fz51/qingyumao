package cn.qingyumao.user.auto;

import cn.qingyumao.user.adapter.account.listener.AccountRegisteredEventListener;
import cn.qingyumao.user.adapter.account.listener.AccountRegisteredEventListener01;
import cn.qingyumao.user.adapter.account.web.AccountController;
import cn.qingyumao.user.app.account.executor.AccountCreateCmdExe;
import cn.qingyumao.user.domain.account.IAccountRepository;
import cn.qingyumao.user.infrastructure.account.repository.AccountRepositoryImpl;
import cn.qingyumao.user.infrastructure.account.repository.dao.UserAccountDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(value = {AccountProperties.class})
public class AccountAutoConfiguration {

    @ConditionalOnMissingBean(AccountController.class)
    @Bean
    public AccountController accountController() {
        return new AccountController();
    }

    @ConditionalOnMissingBean(AccountCreateCmdExe.class)
    @Bean
    public AccountCreateCmdExe accountCreateCmdExe() {
        return new AccountCreateCmdExe();
    }

    @ConditionalOnMissingBean(IAccountRepository.class)
    @Bean
    public IAccountRepository accountRepository() {
        return new AccountRepositoryImpl();
    }


    @ConditionalOnMissingBean(UserAccountDao.class)
    @ComponentScan("cn.qingyumao.user.infrastructure.account.repository.dao")
    @Configuration
    static class DaoRepository {

    }


    @ConditionalOnMissingBean(AccountRegisteredEventListener.class)
    @Bean
    public AccountRegisteredEventListener accountRegisteredEventListener() {
        return new AccountRegisteredEventListener();
    }

    @ConditionalOnMissingBean(AccountRegisteredEventListener01.class)
    @Bean
    public AccountRegisteredEventListener01 accountRegisteredEventListener01() {
        return new AccountRegisteredEventListener01();
    }


}
