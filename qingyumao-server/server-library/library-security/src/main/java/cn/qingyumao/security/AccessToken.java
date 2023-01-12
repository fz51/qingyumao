package cn.qingyumao.security;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author fz51
 */
public class AccessToken {

    /**
     * 认证的用户
     */
    @Getter
    private UsrId userId;

    /**
     * 访问token
     */
    private String token;
    /**
     * 登录依据
     */
    private String voucherId;
    /**
     * token 生成时间
     */
    private LocalDateTime generateTime = LocalDateTime.now();

    /**
     * 过期时间
     */
    private LocalDateTime expirationTime;

    public AccessToken(UsrId userId, String token, LocalDateTime expirationTime) {
        this.token = token;
        this.userId = userId;
        this.voucherId = voucherId;
        this.expirationTime = expirationTime;
    }

    public String getToken() {
        return token;
    }

    public String getVoucherId() {
        return this.voucherId;
    }

    /**
     * token 是否过期
     *
     * @return
     */
    public boolean isExpiration() {
        return expirationTime.compareTo(LocalDateTime.now()) > 0;
    }
}
