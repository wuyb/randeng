package com.randeng.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inventory")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_inventory", allocationSize = 1)
public class Inventory extends BaseEntity<Long> {
    private Fundraising fundraising;
    private Date inventoryDate;
    private List<String> fieldPhotos;

    @OneToOne(optional=true, mappedBy="inventory")
    @JsonIgnore
    public Fundraising getFundraising() {
        return fundraising;
    }

    public void setFundraising(Fundraising fundraising) {
        this.fundraising = fundraising;
    }

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getInventoryDate() {
        return inventoryDate;
    }

    public void setInventoryDate(Date inventoryDate) {
        this.inventoryDate = inventoryDate;
    }

    @Column(nullable = false)
    @ElementCollection(targetClass=String.class)
    public List<String> getFieldPhotos() {
        return fieldPhotos;
    }

    public void setFieldPhotos(List<String> fieldPhotos) {
        this.fieldPhotos = fieldPhotos;
    }
}
