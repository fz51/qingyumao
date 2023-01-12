package cn.qingyumao.user.domain.account.model;

import lombok.Getter;

import java.io.Serializable;

public class AccountDescription implements Serializable {

    @Getter
    private String value;

    public AccountDescription(String descp) {
        if (descp != null && descp.length() > 300) {
            throw new IllegalArgumentException("账户描述字符过长");
        }
        this.value = descp;
    }
}
