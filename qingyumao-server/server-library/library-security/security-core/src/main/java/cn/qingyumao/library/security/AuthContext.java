package cn.qingyumao.library.security;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


/**
 * 安全上下文对象
 *
 * @author : fz51
 * @date : 2022/1/25 5:15 下午
 */
@Slf4j
public class AuthContext {

    private static final ThreadLocal<AuthContext> contextHolder = new ThreadLocal<>();

    private AuthUsr authUsr;

    // ~ Methods
    // ========================================================================================================


    public void clearContext() {
        contextHolder.remove();
    }

    private static AuthContext getContext() {
        AuthContext ctx = contextHolder.get();
        if (ctx == null) {
            ctx = new AuthContext();
            contextHolder.set(ctx);
        }

        return ctx;
    }

    /**
     * 获取认证信息
     *
     * @return
     */
    public static Optional<AuthUsr> getAuthUsr() {
        return Optional.ofNullable(getContext().authUsr);
    }

    /**
     * 判断当前线程（请求）用户是否已经认证
     *
     * @return
     */
    public boolean isAuthenticated() {
        return getAuthUsr().isPresent() && getAuthUsr().get().isAuthenticated();
    }

    /**
     * 设置认证信息
     *
     * @param authUsr
     */
    public void setAuthUsr(AuthUsr authUsr) {
        clearContext();
        getContext().authUsr = authUsr;
    }


}
