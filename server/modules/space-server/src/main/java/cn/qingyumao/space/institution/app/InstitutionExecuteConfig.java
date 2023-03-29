package cn.qingyumao.space.institution.app;

import cn.qingyumao.scaffold.ddd.annotation.AggregateRootGenerator;
import cn.qingyumao.space.institution.domain.Institution;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InstitutionExecuteConfig {

    @AggregateRootGenerator
    public Institution create(@NotNull CreateInstitutionCmd createCmd) {
        final Institution institution = Institution.create(createCmd.getSpace(), createCmd.getName());
        createCmd.setInstitutionId(institution.getId());
        return institution;
    }
}
