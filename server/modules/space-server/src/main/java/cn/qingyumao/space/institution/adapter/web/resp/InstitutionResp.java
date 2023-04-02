package cn.qingyumao.space.institution.adapter.web.resp;

import cn.hutool.core.bean.BeanUtil;
import cn.qingyumao.space.institution.infrastructure.repository.dao.InstitutionDO;
import lombok.Data;

@Data
public class InstitutionResp {
    private Long id;

    private Integer dataVersion;

    private String name;

    private String avatar;

    private String code;

    private String description;

    public static InstitutionResp formatFromDO(InstitutionDO institutionDO) {
        final InstitutionResp resp = new InstitutionResp();
        BeanUtil.copyProperties(institutionDO, resp);
        return resp;
    }
}
