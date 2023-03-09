package cn.qingyumao.library.storage.autoconfig;

import cn.qingyumao.library.security.AuthContext;
import cn.qingyumao.library.security.AuthUsr;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class StorageAutoConfiguration {

    /**
     * 自动填充实体的 creatorId 字段
     */
    @Configuration
    static class SecurityUserAuditorAware implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {
            final Optional<AuthUsr> authUsr = AuthContext.getAuthUsr();
            return authUsr.map(usr -> usr.getId().externalId()).or(() -> Optional.of("Nameless"));
        }
    }

}
