package cn.qingyumao.scaffold.execution;

@FunctionalInterface
public interface CmdMatcher {

    Class<?> matchCmdType();

}
