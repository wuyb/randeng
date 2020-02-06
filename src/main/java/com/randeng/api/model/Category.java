package com.randeng.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_category", allocationSize = 1)
public class Category extends BaseEntity<Long> {
    private String name;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
