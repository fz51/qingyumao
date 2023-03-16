package cn.qingyumao.doc.book.domain;

import lombok.Getter;

/**
 * 目录名称
 */
public class CatalogName {

    @Getter
    private final String val;


    private CatalogName(String name) {
        this.val = name;
    }

}
