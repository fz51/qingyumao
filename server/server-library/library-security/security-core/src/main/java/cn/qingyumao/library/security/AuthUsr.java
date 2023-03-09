package cn.qingyumao.library.security;

import lombok.Data;

@Data
public class AuthUsr {

    private UsrId id;

    private String name;

    private AccessToken token;


    public AuthUsr(UsrId id) {
        this.id = id;
    }

    /**
     * 是否认证
     *
     * @return
     */
    public boolean isAuthenticated() {
        return token != null && token.isExpiration();
    }

}
