package cn.qingyumao.scaffold.execution;


import org.springframework.core.GenericTypeResolver;

public class MethodTest {

    public static void main(String[] args) {
        final Class<?> aClass = GenericTypeResolver.resolveTypeArgument(Cmd01Executor.class, CmdExecutor.class);

        System.out.println(aClass);
    }
}
