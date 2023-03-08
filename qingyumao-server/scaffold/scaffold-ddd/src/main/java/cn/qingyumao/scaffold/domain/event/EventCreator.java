package cn.qingyumao.scaffold.domain.event;

import lombok.Getter;

import java.io.Serializable;

public class EventCreator implements Serializable {

    /**
     * 事件创建人 id，系统里保证唯一。可以通过id 来查询用户信息
     */
    @Getter
    private String id;

    /**
     * 事件创建人名称。用于显示、查询
     */
    @Getter
    private String name;


    public EventCreator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public EventCreator(String id) {
        this.id = id;
        this.name = id;
    }
}
