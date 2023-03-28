package cn.qingyumao.doc.book.domain;

import cn.qingyumao.scaffold.ddd.annotation.Value;
import lombok.Getter;

@Value
public class BookName {

    @Getter
    private final String val;


    public BookName(String name) {
        this.val = name;
    }
}
