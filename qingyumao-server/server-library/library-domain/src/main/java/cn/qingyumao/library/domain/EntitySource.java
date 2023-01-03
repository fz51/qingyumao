package cn.qingyumao.library.domain;

/**
 * 领域实体对象来源
 */
public enum EntitySource {
    /**
     * 仓库加载
     */
    REPOSITORY_LOAD,
    /**
     * 命令创建
     */
    CMD_CREATE,
    ;
}
