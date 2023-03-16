package cn.qingyumao.doc.book.adapter.web;


import cn.qingyumao.doc.book.app.CreateBookCmd;
import cn.qingyumao.doc.book.domain.BookName;
import cn.qingyumao.scaffold.web.CommonController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Long createBook() {
        final CreateBookCmd cmd = new CreateBookCmd(new BookName("测试"));
        handleCmd(cmd);
        return cmd.getResult().getVal();
    }
}
