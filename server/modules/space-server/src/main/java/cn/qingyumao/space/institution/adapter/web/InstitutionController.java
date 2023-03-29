package cn.qingyumao.space.institution.adapter.web;

import cn.qingyumao.scaffold.web.CommonController;
import cn.qingyumao.space.institution.adapter.web.req.InstitutionInfoModifyReq;
import cn.qingyumao.space.institution.app.CreateInstitutionCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/space/institution")
@Slf4j
public class InstitutionController extends CommonController {

    @PostMapping("/create")
    public Long createBook(@RequestBody @Validated(InstitutionInfoModifyReq.CreateGroup.class) InstitutionInfoModifyReq param) {
        final CreateInstitutionCmd cmd = param.generateCreateInstitutionCmd();
        handleCmd(cmd);
        return cmd.getResult().getVal();
    }

}
