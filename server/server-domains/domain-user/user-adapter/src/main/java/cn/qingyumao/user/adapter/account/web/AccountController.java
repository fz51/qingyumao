package cn.qingyumao.user.adapter.account.web;

import cn.qingyumao.library.domain.types.MobileNumber;
import cn.qingyumao.user.adapter.account.web.req.AccountInfoModifyReq;
import cn.qingyumao.user.app.account.executor.AccountCreateCmd;
import cn.qingyumao.user.app.account.executor.AccountCreateCmdExe;
import cn.qingyumao.user.domain.account.model.AccountDescription;
import cn.qingyumao.user.domain.account.model.AccountUniqueCode;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/account")
@Slf4j
public class AccountController {

    @Resource
    private AccountCreateCmdExe accountCreateCmdExe;

    /**
     * 新增一个账户信息
     *
     * @param param
     * @return
     */
    @PostMapping("/register")
    public void registerAccount(@RequestBody @Validated(AccountInfoModifyReq.CreateGroup.class) AccountInfoModifyReq param) {
        AccountCreateCmd cmd = new AccountCreateCmd();
        cmd.setEnabled(param.getEnabled() == 1);
        cmd.setUniqueCode(new AccountUniqueCode(param.getUniqueCode()));
        cmd.setDescription(new AccountDescription(param.getDescription()));
        cmd.setContactMobile(new MobileNumber(param.getContactMobile()));
        accountCreateCmdExe.execute(cmd);
    }
}
