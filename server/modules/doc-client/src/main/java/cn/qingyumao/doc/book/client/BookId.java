package cn.qingyumao.doc.book.client;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

public class BookId implements Serializable {

    @Getter
    private final Long val;

    public BookId(Long id) {
        this.val = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookId bookId = (BookId) o;

        return Objects.equals(val, bookId.val);
    }

    @Override
    public int hashCode() {
        return val != null ? val.hashCode() : 0;
    }
}
