package cn.qingyumao.user.adapter.account.web.req;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author : fz51
 * @date : 2022/2/28 1:47 下午
 */
@Data
public class AccountInfoModifyReq {

    public interface CreateGroup {
    }

    public interface EditGroup {
    }

    @NotNull(groups = EditGroup.class, message = "accountId 必填")
    @Null(groups = CreateGroup.class, message = "新增账户请确保id为空")
    private Long id;


    @NotEmpty(groups = {CreateGroup.class}, message = "账户唯一编码不能为空")
    @Size(groups = {CreateGroup.class}, max = 200, message = "账户唯一编码不能超过200字符")
    private String uniqueCode;

    /**
     * 手机号码注释
     */
    @NotEmpty(groups = {CreateGroup.class, EditGroup.class}, message = "联系手机号码不能为空")
    private String contactMobile;

    @Size(groups = {CreateGroup.class, EditGroup.class}, max = 500, message = "描述不能超过500字符")
    private String description;

    /**
     * 0：不开启；1：开启
     */
    @NotNull(groups = {CreateGroup.class, EditGroup.class}, message = "状态不能为空")
    private Integer enabled;

    @NotNull(groups = EditGroup.class, message = "请输入数据版本号")
    private Integer dataVersion;


}
