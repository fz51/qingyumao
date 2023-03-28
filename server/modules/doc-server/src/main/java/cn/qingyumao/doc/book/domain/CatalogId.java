package cn.qingyumao.doc.book.domain;

import lombok.Getter;

import java.io.Serializable;

public class CatalogId implements Serializable {
    @Getter
    private final Long val;


    public CatalogId(Long val) {
        this.val = val;
    }
}
