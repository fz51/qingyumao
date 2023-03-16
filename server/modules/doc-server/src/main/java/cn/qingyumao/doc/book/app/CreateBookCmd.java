package cn.qingyumao.doc.book.app;

import cn.qingyumao.doc.book.client.BookId;
import cn.qingyumao.doc.book.domain.Book;
import cn.qingyumao.doc.book.domain.BookName;
import cn.qingyumao.scaffold.execution.CmdResult;
import cn.qingyumao.scaffold.execution.annation.Command;
import lombok.Getter;
import lombok.Setter;

@Command(Book.class)
public class CreateBookCmd implements CmdResult<BookId> {
    @Getter
    private final BookName name;

    @Setter
    private BookId bookId;

    public CreateBookCmd(BookName name) {
        this.name = name;
    }

    @Override
    public BookId getResult() {
        return this.bookId;
    }
}
