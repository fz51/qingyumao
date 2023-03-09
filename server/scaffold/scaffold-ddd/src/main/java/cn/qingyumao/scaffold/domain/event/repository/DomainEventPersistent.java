package cn.qingyumao.scaffold.domain.event.repository;

import cn.qingyumao.scaffold.domain.event.EventStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "domain_event")
@Entity
@Data
public class DomainEventPersistent {

    @Id
    private Long id;

    private String traceId;

    private LocalDateTime createTime;

    private String creatorId;

    private String creatorName;

    private EventStatus status;

    private String sourceDescription;

    private String payload;

}
