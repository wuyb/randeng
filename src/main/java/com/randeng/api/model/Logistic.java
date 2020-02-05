package com.randeng.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "logistic")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_logistic", allocationSize = 1)
public class Logistic extends BaseEntity<Long> {
    public enum LogisticType {
        DISTRIBUTOR,
        VOLUNTEER
    }
    public enum LogisticStatus {
        SHIPPED,
        DELIVERED
    }
    private Fundraising fundraising;
    private Hospital hospital;
    private BigDecimal amount;
    private Date shippingDate;
    private Date expectedDeliveryDate;
    private Date actualDeliveryDate;
    private LogisticType logisticType;
    private String distributorTicketNo;
    private BigDecimal distributorCharge;
    private List<String> distributorInvoicePhotos;
    private List<String> distributorTransferPhotos;
    private List<String> distributorFieldPhotos;
    private String volunteerName;
    private List<String> volunteerFieldPhotos;
    private List<String> deliveryPhotos;
    private LogisticStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fundraising_id", nullable = false)
    @JsonIgnore
    public Fundraising getFundraising() {
        return fundraising;
    }

    public void setFundraising(Fundraising fundraising) {
        this.fundraising = fundraising;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "hospital_id", nullable = false)
    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Column(nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    @Column(nullable = false)
    public LogisticType getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(LogisticType logisticType) {
        this.logisticType = logisticType;
    }

    @Column
    public String getDistributorTicketNo() {
        return distributorTicketNo;
    }

    public void setDistributorTicketNo(String distributorTicketNo) {
        this.distributorTicketNo = distributorTicketNo;
    }

    @Column
    public BigDecimal getDistributorCharge() {
        return distributorCharge;
    }

    public void setDistributorCharge(BigDecimal distributorCharge) {
        this.distributorCharge = distributorCharge;
    }

    @Column
    @ElementCollection(targetClass=String.class)
    public List<String> getDistributorInvoicePhotos() {
        return distributorInvoicePhotos;
    }

    public void setDistributorInvoicePhotos(List<String> distributorInvoicePhotos) {
        this.distributorInvoicePhotos = distributorInvoicePhotos;
    }

    @Column
    @ElementCollection(targetClass=String.class)
    public List<String> getDistributorTransferPhotos() {
        return distributorTransferPhotos;
    }

    public void setDistributorTransferPhotos(List<String> distributorTransferPhotos) {
        this.distributorTransferPhotos = distributorTransferPhotos;
    }

    @Column
    @ElementCollection(targetClass=String.class)
    public List<String> getDistributorFieldPhotos() {
        return distributorFieldPhotos;
    }

    public void setDistributorFieldPhotos(List<String> distributorFieldPhotos) {
        this.distributorFieldPhotos = distributorFieldPhotos;
    }

    @Column
    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    @Column
    @ElementCollection(targetClass=String.class)
    public List<String> getVolunteerFieldPhotos() {
        return volunteerFieldPhotos;
    }

    public void setVolunteerFieldPhotos(List<String> volunteerFieldPhotos) {
        this.volunteerFieldPhotos = volunteerFieldPhotos;
    }

    @Column
    @ElementCollection(targetClass=String.class)
    public List<String> getDeliveryPhotos() {
        return deliveryPhotos;
    }

    public void setDeliveryPhotos(List<String> deliveryPhotos) {
        this.deliveryPhotos = deliveryPhotos;
    }

    @Column(nullable = false)
    public LogisticStatus getStatus() {
        return status;
    }

    public void setStatus(LogisticStatus status) {
        this.status = status;
    }
}
