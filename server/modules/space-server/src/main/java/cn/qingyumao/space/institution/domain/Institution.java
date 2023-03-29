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


    private InstitutionName name;

    private InstitutionAvatar avatar;

    @BusinessId
    private InstitutionSpace space;


    public Institution(InstitutionId id) {
        this.id = id;
    }

    public static Institution create(InstitutionSpace space, InstitutionName name) {
        final IdGenerator<InstitutionId> idGenerator = IDGeneratorHolder.getIdGenerator(InstitutionId.class);
        final Institution institution = new Institution(idGenerator.generate());
        institution.space = space;
        institution.name = name;
        return institution;
    }



}
