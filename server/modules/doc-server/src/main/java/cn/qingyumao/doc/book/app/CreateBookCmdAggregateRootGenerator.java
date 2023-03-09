package cn.qingyumao.doc.book.app;

import cn.qingyumao.doc.book.domain.Book;
import cn.qingyumao.scaffold.execution.AggregateGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateBookCmdAggregateRootGenerator implements AggregateGenerator<CreateBookCmd, Book> {


    @Override
    public Book generateAggregateRoot(CreateBookCmd cmd) {
        return Book.create(cmd.getName());
    }
}
