package cn.qingyumao.doc.book.client;

import lombok.Getter;

import java.io.Serializable;

public class BookCreatedEvent implements Serializable {

    @Getter
    private final String bookName;


    public BookCreatedEvent(String bookName) {
        this.bookName = bookName;
    }
}
