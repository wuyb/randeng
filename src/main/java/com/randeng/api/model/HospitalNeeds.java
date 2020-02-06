package com.randeng.api.model;


import javax.persistence.*;

@Entity
@Table(name = "hospital_needs")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_hospital_needs", allocationSize = 1)
public class HospitalNeeds extends BaseEntity<Long> {


    private String hospitalName;
    private String contactName;
    private String contactMethod;
    private String articleName;
    private String pictureUrl;
    private Long state;
    private String remarks;

    @Column(name = "hospital_name",nullable = false)
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Column(name = "contact_name",nullable = false)
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Column(name = "contact_method",nullable = false)
    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    @Column(name = "article_name",nullable = false)
    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    @Column(name = "picture_url",nullable = false)
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Column(name = "state",nullable = true)
    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    @Column(name = "remarks",nullable = true)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
