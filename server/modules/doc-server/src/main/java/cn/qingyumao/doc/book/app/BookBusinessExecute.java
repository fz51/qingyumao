package cn.qingyumao.doc.book.app;

import cn.qingyumao.doc.book.domain.Book;
import cn.qingyumao.scaffold.ddd.execution.BusinessExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookBusinessExecute implements BusinessExecutor<ChangeNameCmd, Book> {


    @Override
    public void doExecute(ChangeNameCmd cmd, Book book) {
        book.changeName(cmd.getName());
    }
}
