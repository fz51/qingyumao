package cn.qingyumao.space.institution.infrastructure.repository;

import cn.hutool.core.collection.CollUtil;
import cn.qingyumao.scaffold.ddd.annotation.AggregateCommit;
import cn.qingyumao.scaffold.ddd.annotation.AggregateLoader;
import cn.qingyumao.space.institution.InstitutionId;
import cn.qingyumao.space.institution.domain.Institution;
import org.javers.core.Javers;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstitutionRepository {

    @Autowired
    private Javers javers;

    @AggregateLoader
    public Institution loadById(InstitutionId institutionId) {
        JqlQuery query = QueryBuilder.byInstanceId(institutionId, Institution.class).build();
        final List<Shadow<Institution>> shadows = javers.findShadows(query);
        if (CollUtil.isNotEmpty(shadows)) {
            return shadows.get(0).get();
        }
        return null;
    }

    @AggregateCommit
    public void commit(Institution institution) {
        javers.commit("sys", institution);
    }
}
