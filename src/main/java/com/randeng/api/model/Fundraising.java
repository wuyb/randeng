package com.randeng.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fundraising")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_fundraising", allocationSize = 1)
public class Fundraising extends BaseEntity<Long> {
    public enum FundRaisingStatus {
        OPEN,
        RAISING,
        SUCCESS,
        EXPIRED,
        PURCHASING,
        PURCHASED,
        TRANSITION,
        DELIVERED,
        CANCELED
    }
    private String name;
    private String manufacturer;
    private String origin;
    private String brand;
    private List<String> productPhotos;
    private String specification;
    private String location;
    private List<String> certificatePhotos;
    private BigDecimal totalPrice;
    private BigDecimal amount;
    private List<Hospital> hospitals;
    private Date startTime;
    private Date endTime;
    private FundRaisingStatus status;

    private Inventory inventory;
    private List<Logistic> logistics;
    private Category category;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Column(nullable = false)
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Column(nullable = false)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(nullable = false)
    @ElementCollection(targetClass=String.class)
    public List<String> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<String> productPhotos) {
        this.productPhotos = productPhotos;
    }

    @Column(nullable = false)
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Column(nullable = false)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(nullable = false)
    @ElementCollection(targetClass=String.class)
    public List<String> getCertificatePhotos() {
        return certificatePhotos;
    }

    public void setCertificatePhotos(List<String> certificatePhotos) {
        this.certificatePhotos = certificatePhotos;
    }

    @Column(nullable = false)
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "fundraising_hospital",
            joinColumns = @JoinColumn(name = "fundraising_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    )
    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(nullable = false)
    public FundRaisingStatus getStatus() {
        return status;
    }

    public void setStatus(FundRaisingStatus status) {
        this.status = status;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @OneToMany(mappedBy = "fundraising", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Logistic> getLogistics() {
        return logistics;
    }

    public void setLogistics(List<Logistic> logistics) {
        this.logistics = logistics;
    }

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false, updatable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
