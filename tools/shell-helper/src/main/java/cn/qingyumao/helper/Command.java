package cn.qingyumao.helper;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class Command {
    // 为一个命令指定多个名称
    @ShellMethod(value = "Add numbers.", key = {"sum", "addition"})
    public void add(int a, int b) {
        int sum = a + b;
        System.out.println(String.format("%d + %d = %d", a, b, sum));
    }
}
