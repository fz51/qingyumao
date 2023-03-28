package cn.qingyumao.doc.book.adapter.web.req;

import cn.qingyumao.doc.book.app.ChangeNameCmd;
import cn.qingyumao.doc.book.app.CreateBookCmd;
import cn.qingyumao.doc.book.client.BookId;
import cn.qingyumao.doc.book.domain.BookName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookInfoModifyReq {

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

    public ChangeNameCmd generateChangeNameCmd() {
        return new ChangeNameCmd(new BookId(id), new BookName(name));
    }

    public CreateBookCmd generateCreateBookCmd() {
        return new CreateBookCmd(new BookName(name));
    }


}
