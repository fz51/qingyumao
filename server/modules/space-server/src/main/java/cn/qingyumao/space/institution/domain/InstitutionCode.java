package cn.qingyumao.space.institution.domain;

import lombok.Getter;
import org.javers.core.metamodel.annotation.ValueObject;

@ValueObject
public class InstitutionCode {
    /**
     * 唯一编码
     */
    @Getter
    private final String val;


    public InstitutionCode(String code) {
        this.val = code;
    }
}
