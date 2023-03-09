package cn.qingyumao.doc.book.client;

import cn.qingyumao.scaffold.domain.Id;
import lombok.Getter;

public class BookId implements Id {

    @Getter
    private final Long val;


    public BookId(Long id) {
        this.val = id;
    }
}
