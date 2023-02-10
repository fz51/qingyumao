package cn.qingyumao.user.infrastructure.account.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountDao extends JpaRepository<UserAccountDO, Long> {

    Optional<UserAccountDO> findByUniqueCodeEquals(String uniqueCode);

}
