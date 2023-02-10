package cn.qingyumao.user.auto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "qingyumao.domain.user")
@Data
public class AccountProperties {

    private boolean enabled = false;

}
