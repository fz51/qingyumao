package cn.qingyumao.space.institution.infrastructure.repository.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

/**
 * 机构企业
 */
@Data
@Entity
@Table(name = "space_institution")
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
public class InstitutionDO implements Persistable<Long> {

    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "creator_id")
    @CreatedBy
    private String creatorId;

    @Basic
    @Column(updatable = false, name = "create_time")
    @CreatedDate
    private Timestamp createTime;

    @Basic
    @Column(name = "updater_id")
    private String updaterId;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;
    @Basic
    @Column(name = "deleted")
    private Short deleted;
    @Basic
    @Column(name = "data_version")
    private Integer dataVersion;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "avatar")
    private String avatar;

    @Basic
    @Column(name = "code")
    private String code;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "enabled")
    private Short enabled;

    @Transient
    private boolean isNew;

    public InstitutionDO() {

    }
}
