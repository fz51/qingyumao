package cn.qingyumao.space.institution.adapter.web.req;

import cn.qingyumao.space.institution.app.CreateInstitutionCmd;
import cn.qingyumao.space.institution.domain.InstitutionName;
import cn.qingyumao.space.institution.domain.InstitutionCode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InstitutionInfoModifyReq {

    public interface CreateGroup {
    }

    public interface EditGroup {
    }

    @NotNull(groups = EditGroup.class, message = "accountId 必填")
    @Null(groups = CreateGroup.class, message = "新增账户请确保id为空")
    private Long id;


    @NotEmpty(groups = {CreateGroup.class}, message = "名称不能为空")
    @Size(groups = {CreateGroup.class}, max = 200, message = "名称不能超过200字符")
    private String name;

    @NotEmpty(groups = {CreateGroup.class}, message = "空间不能为空")
    @Size(groups = {CreateGroup.class}, max = 50, message = "空间不能超过50字符")
    private String code;

    public CreateInstitutionCmd generateCreateInstitutionCmd() {
        return new CreateInstitutionCmd(new InstitutionCode(code), new InstitutionName(this.name));
    }


}
