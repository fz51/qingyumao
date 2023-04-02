package cn.qingyumao.space.institution.infrastructure.repository;

import cn.hutool.core.collection.CollUtil;
import cn.qingyumao.scaffold.ddd.annotation.AggregateCommit;
import cn.qingyumao.scaffold.ddd.annotation.AggregateLoader;
import cn.qingyumao.space.institution.InstitutionId;
import cn.qingyumao.space.institution.domain.Institution;
import cn.qingyumao.space.institution.infrastructure.repository.dao.InstitutionDO;
import cn.qingyumao.space.institution.infrastructure.repository.dao.InstitutionDao;
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

    @Autowired
    private InstitutionDao institutionDao;

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
        if (loadById(institution.getId()) == null) {
            // 新增
            institutionDao.save(formatDO(institution));
        } else {
            // 编辑
            institutionDao.save(formatDO(institution));
        }
    }

    private InstitutionDO formatDO(Institution institution) {
        return InstitutionDO.builder()
                .id(institution.getId().getVal())
                .name(institution.getName().getVal())
                .code(institution.getCode().getVal())
                // .description(institution.g)
                .build();
    }
}
