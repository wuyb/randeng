package com.randeng.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enterprise_call")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_enterprise_call", allocationSize = 1)
public class EnterpriseCall extends BaseEntity<Long> {

    private String enterpriseName;
    private String contact;
    private String mobile;
    private String donationsName;
    private String donationsAmount;
    private List<String> photos;
    private List<EnterpriseCallRecord> records = new ArrayList<>();

    @Column(nullable = false)
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

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
    public String getDonationsName() {
        return donationsName;
    }

    public void setDonationsName(String donationsName) {
        this.donationsName = donationsName;
    }

    @Column(nullable = false)
    public String getDonationsAmount() {
        return donationsAmount;
    }

    public void setDonationsAmount(String donationsAmount) {
        this.donationsAmount = donationsAmount;
    }

    @Column
    @ElementCollection(targetClass=String.class)
    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @OneToMany(targetEntity = EnterpriseCallRecord.class, cascade = CascadeType.ALL, mappedBy="call")
    public List<EnterpriseCallRecord> getRecords() {
        return records;
    }

    public void setRecords(List<EnterpriseCallRecord> records) {
        this.records = records;
    }

}
