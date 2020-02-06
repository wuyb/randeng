package com.randeng.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "enterprise")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_enterprise", allocationSize = 1)
public class Enterprise extends BaseEntity<Long> {

    private String enterpriseName;
    private String contactName;
    private String contactMethod;
    private String donationsName;
    private String donationsAmount;
    private Long state;
    private String remarks;

    @Column(nullable = false)
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    @Column(nullable = false)
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Column(nullable = false)
    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
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

    @Column(nullable = true)
    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    @Column(nullable = true)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
