package cn.qingyumao.doc.book.domain;

import cn.qingyumao.doc.book.client.BookId;
import cn.qingyumao.scaffold.ddd.annotation.AggregateRootEntity;
import cn.qingyumao.scaffold.ddd.annotation.BusinessId;
import cn.qingyumao.scaffold.ddd.annotation.EntityId;
import cn.qingyumao.scaffold.ddd.domain.IDGeneratorHolder;
import cn.qingyumao.scaffold.ddd.domain.IdGenerator;
import lombok.Getter;

@AggregateRootEntity
public class Book {

    @Getter
    @EntityId
    private final BookId id;


    @Getter
    @BusinessId
    private BookName name;

    /**
     * 目录
     */
    private Catalog catalog;

    public Book(BookId id) {
        this.id = id;
    }

    public static Book create(BookName name) {
        final IdGenerator<BookId> bookIdIdGenerator = IDGeneratorHolder.getIdGenerator(BookId.class);
        final BookId bookId = bookIdIdGenerator.generate();
        final Book book = new Book(bookId);
        book.name = name;
        return book;
    }

    /**
     * 新建目录
     *
     * @param name
     * @param parentId 新增目录的父节点。如果为空，表示创建到目录是根目录
     */
    public void addCatalog(CatalogName name, CatalogId parentId) {

    }


    public void changeName(BookName name) {
        this.name = name;
    }

}
