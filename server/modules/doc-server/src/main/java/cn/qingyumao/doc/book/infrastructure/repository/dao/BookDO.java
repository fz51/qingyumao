package cn.qingyumao.doc.book.infrastructure.repository.dao;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "doc_book")
public class BookDO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "creator_id")
    private String creatorId;
    @Basic
    @Column(name = "creator_space")
    private String creatorSpace;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "updater_id")
    private String updaterId;
    @Basic
    @Column(name = "updater_space")
    private String updaterSpace;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;
    @Basic
    @Column(name = "delete_flag")
    private Short deleteFlag;
    @Basic
    @Column(name = "data_version")
    private Integer dataVersion;
    @Basic
    @Column(name = "quote")
    private String quote;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "cover")
    private String cover;
    @Basic
    @Column(name = "profiles")
    private String profiles;
    @Basic
    @Column(name = "access_scope")
    private Byte accessScope;
    @Basic
    @Column(name = "last_editing_content_id")
    private Long lastEditingContentId;
    @Basic
    @Column(name = "published_content")
    private String publishedContent;
    @Basic
    @Column(name = "last_editing_content_time")
    private Timestamp lastEditingContentTime;
    @Basic
    @Column(name = "author_id")
    private String authorId;
    @Basic
    @Column(name = "author_type")
    private String authorType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorSpace() {
        return creatorSpace;
    }

    public void setCreatorSpace(String creatorSpace) {
        this.creatorSpace = creatorSpace;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterSpace() {
        return updaterSpace;
    }

    public void setUpdaterSpace(String updaterSpace) {
        this.updaterSpace = updaterSpace;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
    }

    public Byte getAccessScope() {
        return accessScope;
    }

    public void setAccessScope(Byte accessScope) {
        this.accessScope = accessScope;
    }

    public Long getLastEditingContentId() {
        return lastEditingContentId;
    }

    public void setLastEditingContentId(Long lastEditingContentId) {
        this.lastEditingContentId = lastEditingContentId;
    }

    public String getPublishedContent() {
        return publishedContent;
    }

    public void setPublishedContent(String publishedContent) {
        this.publishedContent = publishedContent;
    }

    public Timestamp getLastEditingContentTime() {
        return lastEditingContentTime;
    }

    public void setLastEditingContentTime(Timestamp lastEditingContentTime) {
        this.lastEditingContentTime = lastEditingContentTime;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorType() {
        return authorType;
    }

    public void setAuthorType(String authorType) {
        this.authorType = authorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDO that = (BookDO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;
        if (creatorSpace != null ? !creatorSpace.equals(that.creatorSpace) : that.creatorSpace != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updaterId != null ? !updaterId.equals(that.updaterId) : that.updaterId != null) return false;
        if (updaterSpace != null ? !updaterSpace.equals(that.updaterSpace) : that.updaterSpace != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (deleteFlag != null ? !deleteFlag.equals(that.deleteFlag) : that.deleteFlag != null) return false;
        if (dataVersion != null ? !dataVersion.equals(that.dataVersion) : that.dataVersion != null) return false;
        if (quote != null ? !quote.equals(that.quote) : that.quote != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (cover != null ? !cover.equals(that.cover) : that.cover != null) return false;
        if (profiles != null ? !profiles.equals(that.profiles) : that.profiles != null) return false;
        if (accessScope != null ? !accessScope.equals(that.accessScope) : that.accessScope != null) return false;
        if (lastEditingContentId != null ? !lastEditingContentId.equals(that.lastEditingContentId) : that.lastEditingContentId != null)
            return false;
        if (publishedContent != null ? !publishedContent.equals(that.publishedContent) : that.publishedContent != null)
            return false;
        if (lastEditingContentTime != null ? !lastEditingContentTime.equals(that.lastEditingContentTime) : that.lastEditingContentTime != null)
            return false;
        if (authorId != null ? !authorId.equals(that.authorId) : that.authorId != null) return false;
        if (authorType != null ? !authorType.equals(that.authorType) : that.authorType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (creatorSpace != null ? creatorSpace.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updaterSpace != null ? updaterSpace.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (deleteFlag != null ? deleteFlag.hashCode() : 0);
        result = 31 * result + (dataVersion != null ? dataVersion.hashCode() : 0);
        result = 31 * result + (quote != null ? quote.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (profiles != null ? profiles.hashCode() : 0);
        result = 31 * result + (accessScope != null ? accessScope.hashCode() : 0);
        result = 31 * result + (lastEditingContentId != null ? lastEditingContentId.hashCode() : 0);
        result = 31 * result + (publishedContent != null ? publishedContent.hashCode() : 0);
        result = 31 * result + (lastEditingContentTime != null ? lastEditingContentTime.hashCode() : 0);
        result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
        result = 31 * result + (authorType != null ? authorType.hashCode() : 0);
        return result;
    }
}
