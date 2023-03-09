package cn.qingyumao.library.event.core;

import lombok.Getter;

public class DefaultEvent extends AbsDomainEvent {

    public DefaultEvent(Object resource) {
        super(new CommonSource(resource), new EventCreator("default_event"));
    }

    static final class CommonSource extends EventSource {
        @Getter
        private Object data;

        public CommonSource(Object data) {
            this.data = data;
        }
    }
}
