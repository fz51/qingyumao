package cn.qingyumao.library.domain.types;

import java.io.Serializable;

/**
 * 手机号码
 *
 * @author : fz51
 * @date : 2022/2/28 11:07 上午
 */
public class MobileNumber implements Serializable {

    private String number;

    public MobileNumber(String number) {
        if (number == null) {
            throw new IllegalArgumentException("mobile number must not null");
        }
        if (number.length() >11) {
            // 手机号码不合法
            throw new IllegalArgumentException("Invalid mobile phone number");
        }
        this.number = number;

    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MobileNumber that = (MobileNumber) o;

        return number != null ? number.equals(that.number) : that.number == null;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
