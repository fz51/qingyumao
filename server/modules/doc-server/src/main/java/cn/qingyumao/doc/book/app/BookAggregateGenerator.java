package cn.qingyumao.doc.book.app;

import cn.qingyumao.doc.book.domain.Book;
import cn.qingyumao.scaffold.ddd.annotation.AggregateGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookAggregateGenerator {

    @AggregateGenerator
    public Book generateByCreateBookCmd(CreateBookCmd cmd) {
        log.info("创建书籍");
        final Book book = Book.create(cmd.getName());
        cmd.setBookId(book.getId());
        return book;
    }
}
