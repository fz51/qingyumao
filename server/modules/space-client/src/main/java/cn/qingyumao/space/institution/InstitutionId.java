package cn.qingyumao.space.institution;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 机构id
 */
public class InstitutionId implements Serializable {

    @Getter
    private final Long val;

    public InstitutionId(Long id) {
        this.val = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstitutionId that = (InstitutionId) o;

        return Objects.equals(val, that.val);
    }

    @Override
    public int hashCode() {
        return val != null ? val.hashCode() : 0;
    }
}
