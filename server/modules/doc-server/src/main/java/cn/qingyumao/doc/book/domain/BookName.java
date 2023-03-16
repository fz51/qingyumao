package cn.qingyumao.doc.book.domain;

import lombok.Getter;

public class BookName {

    @Getter
    private final String val;


    public BookName(String name) {
        this.val = name;
    }
}
