package cn.qingyumao.doc.book.adapter.web;


import cn.qingyumao.doc.book.app.CreateBookCmd;
import cn.qingyumao.scaffold.execution.CmdDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doc/book")
@Slf4j
public class BookController {

    @Autowired
    private CmdDispatcher cmdDispatcher;

    @PostMapping("/create")
    public void createBook() {
        log.info("创建文档");
        cmdDispatcher.dispatch(new CreateBookCmd("测试"));
    }
}
