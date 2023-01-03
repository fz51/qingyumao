package cn.qingyumao.library.event;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 对象事件源
 */
public abstract class EventSource implements Serializable {

    /**
     * 备注说明
     */
    @Getter
    @Setter
    private String remark;

}
