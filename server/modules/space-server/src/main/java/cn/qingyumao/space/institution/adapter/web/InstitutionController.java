package cn.qingyumao.space.institution.adapter.web;

import cn.qingyumao.scaffold.web.CommonController;
import cn.qingyumao.space.institution.adapter.web.req.InstitutionInfoModifyReq;
import cn.qingyumao.space.institution.adapter.web.req.InstitutionPageQry;
import cn.qingyumao.space.institution.adapter.web.resp.InstitutionResp;
import cn.qingyumao.space.institution.app.CreateInstitutionCmd;
import cn.qingyumao.space.institution.infrastructure.repository.InstitutionQryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/space/institution")
@Slf4j
public class InstitutionController extends CommonController {


    private InstitutionQryRepository institutionQryRepository;


    @GetMapping("/admin_page")
    public Page<InstitutionResp> adminList4Page(InstitutionPageQry qry) {
        return institutionQryRepository.adminList4Page(qry).map(InstitutionResp::formatFromDO);
    }

    /**
     * 创建一个机构信息
     *
     * @param param
     * @return
     */
    @PostMapping("/create")
    public Long createInstitution(@RequestBody @Validated(InstitutionInfoModifyReq.CreateGroup.class) InstitutionInfoModifyReq param) {
        final CreateInstitutionCmd cmd = param.generateCreateInstitutionCmd();
        handleCmd(cmd);
        return cmd.getResult().getVal();
    }

    @Autowired
    public void setInstitutionQryRepository(InstitutionQryRepository institutionQryRepository) {
        this.institutionQryRepository = institutionQryRepository;
    }
}
