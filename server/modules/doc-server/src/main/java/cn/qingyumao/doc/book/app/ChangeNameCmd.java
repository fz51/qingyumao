package cn.qingyumao.doc.book.app;

import cn.qingyumao.doc.book.client.BookId;
import cn.qingyumao.doc.book.domain.Book;
import cn.qingyumao.doc.book.domain.BookName;
import cn.qingyumao.scaffold.ddd.annotation.Command;
import cn.qingyumao.scaffold.ddd.annotation.LoadVoucher;
import lombok.Getter;
import lombok.Setter;

@Command(Book.class)
public class ChangeNameCmd {
    @Getter
    private final BookName name;

    @LoadVoucher
    @Getter
    private final BookId bookId;

    public ChangeNameCmd(BookId bookId, BookName name) {
        this.name = name;
        this.bookId = bookId;
    }
}
