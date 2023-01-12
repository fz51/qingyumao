package cn.qingyumao.library.domain;

import java.util.Objects;

/**
 * @author fz51
 */
public class StrId implements Id {

    private String value;

    public StrId(String value) {
        if (value == null) {
            throw new IllegalArgumentException("string id must not null");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException("string id must not less than 0");
        }
        this.value = value;
    }

    public static StrId of(String value) {
        return new StrId(value);
    }


    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StrId longId = (StrId) o;

        return Objects.equals(value, longId.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
