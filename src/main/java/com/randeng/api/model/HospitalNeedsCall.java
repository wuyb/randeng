package com.randeng.api.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospital_needs")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_hospital_needs", allocationSize = 1)
public class HospitalNeedsCall extends BaseEntity<Long> {

    private String hospitalName;
    private String contact;
    private String mobile;
    private String productName;
    private List<String> photos;
    private List<HospitalNeedsCallRecord> records = new ArrayList<>();

    @Column(name = "hospital_name",nullable = false)
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Column(name = "contact_name",nullable = false)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name = "contact_method",nullable = false)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "article_name",nullable = false)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column
    @ElementCollection(targetClass=String.class)
    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @OneToMany(targetEntity = HospitalNeedsCallRecord.class, cascade = CascadeType.ALL, mappedBy="call")
    public List<HospitalNeedsCallRecord> getRecords() {
        return records;
    }

    public void setRecords(List<HospitalNeedsCallRecord> records) {
        this.records = records;
    }

}
