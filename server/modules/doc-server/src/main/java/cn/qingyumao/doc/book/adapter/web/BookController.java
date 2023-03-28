package cn.qingyumao.doc.book.adapter.web;


import cn.qingyumao.doc.book.adapter.web.req.BookInfoModifyReq;
import cn.qingyumao.doc.book.app.ChangeNameCmd;
import cn.qingyumao.doc.book.app.CreateBookCmd;
import cn.qingyumao.scaffold.web.CommonController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doc/book")
@Slf4j
public class BookController extends CommonController {
    /**
     * 创建文档
     *
     * @return 文档id
     */
    @PostMapping("/create")
    public Long createBook(@RequestBody @Validated(BookInfoModifyReq.CreateGroup.class) BookInfoModifyReq param) {
        final CreateBookCmd cmd = param.generateCreateBookCmd();
        handleCmd(cmd);
        return cmd.getResult().getVal();
    }

    @PutMapping("/change_name")
    public void changeName(@RequestBody @Validated(BookInfoModifyReq.EditGroup.class) BookInfoModifyReq param) {
        final ChangeNameCmd cmd = param.generateChangeNameCmd();
        handleCmd(cmd);
    }
}
