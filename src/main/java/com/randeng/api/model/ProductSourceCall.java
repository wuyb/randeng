package com.randeng.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_source_call")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_product_source_call", allocationSize = 1)
public class ProductSourceCall extends BaseEntity<Long> {
    private String contact;
    private String mobile;
    private String productName;
    private String productSpec;
    private List<String> photos;
    private List<ProductSourceCallRecord> records = new ArrayList<>();

    @Column(nullable = false)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(nullable = false)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(nullable = false)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(nullable = false)
    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    @Column
    @ElementCollection(targetClass=String.class)
    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @OneToMany(targetEntity = ProductSourceCallRecord.class, cascade = CascadeType.ALL, mappedBy="call")
    public List<ProductSourceCallRecord> getRecords() {
        return records;
    }

    public void setRecords(List<ProductSourceCallRecord> records) {
        this.records = records;
    }
}
