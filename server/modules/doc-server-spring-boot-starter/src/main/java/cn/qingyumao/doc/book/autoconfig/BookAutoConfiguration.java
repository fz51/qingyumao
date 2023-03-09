package cn.qingyumao.doc.book.autoconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        {
                "cn.qingyumao.doc.book"
        }
)
public class BookAutoConfiguration {
}
