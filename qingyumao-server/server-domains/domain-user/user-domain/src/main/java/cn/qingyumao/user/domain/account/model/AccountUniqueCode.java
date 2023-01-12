package cn.qingyumao.user.domain.account.model;

import cn.qingyumao.library.domain.Id;
import lombok.Getter;

import java.io.Serializable;

/**
 * 团队编号
 *
 * @author fz51
 * @date : 2021/12/13 3:20 下午
 */
public class AccountUniqueCode implements Serializable, Id {

    @Getter
    private String value;

    protected AccountUniqueCode() {

    }

    public AccountUniqueCode(String value) {
        this.value = value;
    }

}
