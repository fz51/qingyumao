package cn.qingyumao.space.institution.infrastructure.repository;

import cn.qingyumao.space.institution.adapter.web.req.InstitutionPageQry;
import cn.qingyumao.space.institution.infrastructure.repository.dao.InstitutionDO;
import cn.qingyumao.space.institution.infrastructure.repository.dao.InstitutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public class InstitutionQryRepository {

    @Autowired
    private InstitutionDao institutionDao;

    public Page<InstitutionDO> adminList4Page(InstitutionPageQry pageQry) {

        return institutionDao.findAll(pageQry.specificationFromAdmin(), pageQry);
    }


}
