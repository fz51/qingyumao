package cn.qingyumao.user.infrastructure.account.repository;

import cn.qingyumao.domain.ids.user.AccountId;
import cn.qingyumao.library.domain.EntitySource;
import cn.qingyumao.library.domain.storage.AggregateSaveException;
import cn.qingyumao.library.domain.types.MobileNumber;
import cn.qingyumao.user.domain.account.Account;
import cn.qingyumao.user.domain.account.AccountUUID;
import cn.qingyumao.user.domain.account.IAccountRepository;
import cn.qingyumao.user.domain.account.model.AccountDescription;
import cn.qingyumao.user.domain.account.model.AccountUniqueCode;
import cn.qingyumao.user.infrastructure.account.repository.dao.UserAccountDO;
import cn.qingyumao.user.infrastructure.account.repository.dao.UserAccountDao;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class AccountRepositoryImpl implements IAccountRepository {
    @Resource
    private UserAccountDao userAccountDao;

    @Override
    public Optional<Account> load(AccountUUID id) {

        if (id.getUniqueCode() != null) {
            final Optional<UserAccountDO> optional = userAccountDao.findByUniqueCodeEquals(id.getUniqueCode().getValue());
            if (optional.isPresent()) {
                return Optional.of(formatAR(optional.get()));
            }
        }
        if (id.getId() != null) {
            final Optional<UserAccountDO> optional = userAccountDao.findById(id.getId().getValue());
            if (optional.isPresent()) {
                return Optional.of(formatAR(optional.get()));
            }
        }
        return Optional.empty();
    }

    private Account formatAR(UserAccountDO dataObject) {
        final AccountId accountId = new AccountId(dataObject.getId());
        final AccountUniqueCode uniqueCode = new AccountUniqueCode(dataObject.getUniqueCode());
        return Account.load(accountId, dataObject.getDataVersion(), uniqueCode, new MobileNumber(dataObject.getContactMobile()), dataObject.getEnabled() == 1, new AccountDescription(dataObject.getDescription()));
    }


    @Override
    public void save(Account account) throws AggregateSaveException {
        log.info("save account :{}", account);
        if (EntitySource.ORIGIN.equals(account.getSource())) {
            if (account.isDeleted()) {
                log.warn("管理员信息刚刚新增就删除。{}", account);
                return;
            }
            userAccountDao.saveAndFlush(getDOFromDomain(account));
        } else if (EntitySource.REPOSITORY_LOAD.equals(account.getSource())) {
            if (account.isDeleted()) {
                log.error("管理员信息不支持删除操作");
            } else {
                userAccountDao.saveAndFlush(getDOFromDomain(account));
            }
        }
    }

    private UserAccountDO getDOFromDomain(Account account) {
        UserAccountDO dataObject = UserAccountDO.builder().description(account.getDescription().getValue()).contactMobile(account.getContactMobile().getNumber()).uniqueCode(account.getUniqueCode().getValue()).enabled((short) (account.getEnabled() ? 1 : 0)).isNew(account.getSource().equals(EntitySource.ORIGIN)).build();
        dataObject.setId(account.getId().getValue());
        dataObject.setDataVersion(account.getDataVersion().get());
        return dataObject;
    }
}
