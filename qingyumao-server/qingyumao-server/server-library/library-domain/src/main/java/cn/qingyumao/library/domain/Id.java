package cn.qingyumao.library.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * domain id
 */
public abstract class Id<T> implements Serializable {

    private final T val;

    protected Id(T val) {
        if (val == null) {
            throw new IllegalArgumentException("id must not null");
        }
        this.val = val;
    }

    public T val() {
        return this.val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Id<?> id = (Id<?>) o;
        return Objects.equals(val, id.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return "Id{" +
                "val=" + val +
                '}';
    }
}
