package cn.qingyumao.user.domain.account.model;

import lombok.Getter;

import java.io.Serializable;

/**
 * 团队编号
 *
 * @author fz51
 * @date : 2021/12/13 3:20 下午
 */
public class AccountName implements Serializable {

    @Getter
    private String value;

    public AccountName(String value) {
        this.value = value;
    }

}
