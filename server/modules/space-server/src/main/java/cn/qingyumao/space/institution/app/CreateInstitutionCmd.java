package cn.qingyumao.space.institution.app;

import cn.qingyumao.scaffold.ddd.annotation.Command;
import cn.qingyumao.scaffold.ddd.execution.CmdResult;
import cn.qingyumao.space.institution.InstitutionId;
import cn.qingyumao.space.institution.domain.Institution;
import cn.qingyumao.space.institution.domain.InstitutionName;
import cn.qingyumao.space.institution.domain.InstitutionCode;
import lombok.Getter;
import lombok.Setter;

@Command(Institution.class)
public class CreateInstitutionCmd implements CmdResult<InstitutionId> {

    @Getter
    private final InstitutionCode space;
    @Getter
    private final InstitutionName name;

    public CreateInstitutionCmd(InstitutionCode space, InstitutionName name) {
        this.space = space;
        this.name = name;
    }

    @Setter
    private InstitutionId institutionId;

    @Override
    public InstitutionId getResult() {
        return institutionId;
    }
}
