package cn.qingyumao.doc.book.domain;

import cn.qingyumao.scaffold.domain.Id;
import lombok.Getter;

public class CatalogId implements Id {
    @Getter
    private final Long val;


    public CatalogId(Long val) {
        this.val = val;
    }
}
