package cn.qingyumao.space.institution.domain;

import lombok.Getter;

public class InstitutionName {
    @Getter
    private final String val;

    public InstitutionName(String val) {
        this.val = val;
    }
}
