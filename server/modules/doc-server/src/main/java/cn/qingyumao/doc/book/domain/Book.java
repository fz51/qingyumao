package cn.qingyumao.doc.book.domain;

import cn.qingyumao.doc.book.client.BookId;
import cn.qingyumao.scaffold.domain.AggregateRoot;
import cn.qingyumao.scaffold.domain.EntitySource;
import cn.qingyumao.scaffold.domain.IDGeneratorManager;
import cn.qingyumao.scaffold.domain.IdGenerator;
import lombok.Getter;

public class Book extends AggregateRoot<BookId> {

    @Getter
    private BookName name;

    /**
     * 目录
     */
    private Catalog catalog;

    public Book(BookId id, EntitySource source) {
        super(id, source);
    }

    public static Book create(BookName name) {
        final IdGenerator<BookId> bookIdIdGenerator = IDGeneratorManager.getIdGenerator(BookId.class);
        assert bookIdIdGenerator != null;
        final BookId bookId = bookIdIdGenerator.generate();
        final Book book = new Book(bookId, EntitySource.ORIGIN);
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


}
