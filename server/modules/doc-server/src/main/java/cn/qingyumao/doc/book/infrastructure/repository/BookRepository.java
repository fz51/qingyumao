package cn.qingyumao.doc.book.infrastructure.repository;

import cn.qingyumao.doc.book.domain.Book;
import cn.qingyumao.scaffold.repository.AggregateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookRepository implements AggregateRepository<Book> {

    @Override
    public void save(Book book) {
        log.info("save book");
    }
}
