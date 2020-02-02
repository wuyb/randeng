package com.randeng.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * The base definition for all entities. In general, we have the following specifications for entities.
 * <ul>
 *     <li>Optimistic locks are used for concurrency control. The <c>version</c> attribute and corresponding database column is used for this purpose.</li>
 *     <li>The deletion is soft. The <c>deleted</c> flag is used for this purpose.</li>
 *     <li>Each entity must have a unique id, <c>uuid</c> to avoid exposing the raw incremental <c>id</c>.</li>
 * </ul>
 *
 * @param <ID> the type of the id
 */
@EntityListeners(EntityListener.class)
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = -1779581706102713065L;

    /**
     * The name of the create date property.
     */
    public static final String CREATE_DATE_PROPERTY_NAME = "createDate";

    /**
     * The name of the modify date property.
     */
    public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";

    /**
     * The name of the version property.
     */
    public static final String VERSION_PROPERTY_NAME = "version";

    /**
     * The name of the deleted property.
     */
    public static final String DELETED_PROPERTY_NAME = "deleted";

    /**
     * The raw id of the entity.
     */
    private ID id;

    /**
     * The UUID of the entity. We use this property for single entity query to avoid crawling of the data.
     */
    private String uuid;

    /**
     * The flag to mark whether the entity has been deleted.
     */
    private Boolean deleted = Boolean.FALSE;

    /**
     * The version of the entity. This is used for optimistic lock.
     */
    private Long version;

    /**
     * The create date of the entity.
     */
    private Date createDate;

    /**
     * The modify date of the entity.
     */
    private Date updateDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @JsonIgnore
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Transient
    public boolean isNew() {
        return getId() == null;
    }
}
