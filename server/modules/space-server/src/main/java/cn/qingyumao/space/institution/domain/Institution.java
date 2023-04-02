package cn.qingyumao.space.institution.domain;

import cn.qingyumao.scaffold.ddd.annotation.AggregateRootEntity;
import cn.qingyumao.scaffold.ddd.annotation.BusinessId;
import cn.qingyumao.scaffold.ddd.annotation.EntityId;
import cn.qingyumao.scaffold.ddd.domain.IDGeneratorHolder;
import cn.qingyumao.scaffold.ddd.domain.IdGenerator;
import cn.qingyumao.space.institution.InstitutionId;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * 机构
 */
@AggregateRootEntity
public class Institution {

    @EntityId
    @Id
    @Getter
    private final InstitutionId id;

    @Getter
    private InstitutionName name;
    @Getter
    private InstitutionAvatar avatar;

    @BusinessId
    @Getter
    private InstitutionCode code;


    public Institution(InstitutionId id) {
        this.id = id;
    }

    public static Institution create(InstitutionCode code, InstitutionName name) {
        final IdGenerator<InstitutionId> idGenerator = IDGeneratorHolder.getIdGenerator(InstitutionId.class);
        final Institution institution = new Institution(idGenerator.generate());
        institution.code = code;
        institution.name = name;
        return institution;
    }

}
