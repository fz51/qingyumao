package cn.qingyumao.doc.book.app;

import cn.qingyumao.doc.book.domain.Book;
import cn.qingyumao.scaffold.execution.annation.Command;
import lombok.Getter;

@Command(Book.class)
public class CreateBookCmd {
    @Getter
    private final String name;

    public CreateBookCmd(String name) {
        this.name = name;
    }
}
