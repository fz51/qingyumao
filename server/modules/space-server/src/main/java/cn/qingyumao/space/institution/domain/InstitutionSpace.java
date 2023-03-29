package cn.qingyumao.space.institution.domain;

import lombok.Getter;
import org.javers.core.metamodel.annotation.ValueObject;

@ValueObject
public class InstitutionSpace {
    /**
     * 唯一编码
     */
    @Getter
    private final String code;


    public InstitutionSpace(String code) {
        this.code = code;
    }
}
