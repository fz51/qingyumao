package cn.qingyumao.scaffold.domain;

public interface IdGenerator<ID extends Id> {

    ID generate();

}
