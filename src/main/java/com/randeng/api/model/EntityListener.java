package com.randeng.api.model;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.UUID;

/**
 * The entity listener is used to set the create/modify date for the entity.
 */
public class EntityListener {

    @PrePersist
    public void prePersist(BaseEntity<?> entity) {
        entity.setCreateDate(new Date());
        entity.setUpdateDate(new Date());
        entity.setUuid(UUID.randomUUID().toString());
        entity.setVersion(null);
    }

    @PreUpdate
    public void preUpdate(BaseEntity<?> entity) {
        entity.setUpdateDate(new Date());
    }

}