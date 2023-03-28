package cn.qingyumao.doc.book.infrastructure.repository;

import cn.hutool.core.collection.CollUtil;
import cn.qingyumao.doc.book.client.BookId;
import cn.qingyumao.doc.book.domain.Book;
import cn.qingyumao.scaffold.ddd.annotation.AggregateCommit;
import cn.qingyumao.scaffold.ddd.annotation.AggregateLoader;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BookRepository {

    @Autowired
    private Javers javers;

    @AggregateCommit
    public void commit(Book book) {
        javers.commit("sys", book);
        log.info("save book");
    }

    @AggregateLoader
    public Book loadById(BookId bookId) {
        log.info("loadById book {}", bookId);
        JqlQuery query = QueryBuilder.byInstanceId(bookId, Book.class).build();
        final List<Shadow<Book>> shadows = javers.findShadows(query);
        if (CollUtil.isNotEmpty(shadows)) {
            return shadows.get(0).get();
        }
        return null;
    }
}
