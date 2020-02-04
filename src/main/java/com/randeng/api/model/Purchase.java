package com.randeng.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchase")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_purchase", allocationSize = 1)
public class Purchase extends BaseEntity<Long> {

    public enum PurchaseStatus {
        PURCHASED,
        STORED,
        SHIPPED,
        DELIVERED
    }

    private List<Fundraising> fundraisings;
    private String purchaser;
    private Date purchaseDate;
    private BigDecimal amount;
    private List<String> invoicePhotos;
    private List<String> transferPhotos;
    private List<String> fieldPhotos;
    private PurchaseStatus status;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "purchase_fundraising",
            joinColumns = @JoinColumn(name = "purchase_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fundraising_id", referencedColumnName = "id")
    )
    public List<Fundraising> getFundraisings() {
        return fundraisings;
    }

    public void setFundraisings(List<Fundraising> fundraisings) {
        this.fundraisings = fundraisings;
    }

    @Column(nullable = false)
    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Column(nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(nullable = false)
    @ElementCollection(targetClass=String.class)
    public List<String> getInvoicePhotos() {
        return invoicePhotos;
    }

    public void setInvoicePhotos(List<String> invoicePhotos) {
        this.invoicePhotos = invoicePhotos;
    }

    @Column(nullable = false)
    @ElementCollection(targetClass=String.class)
    public List<String> getTransferPhotos() {
        return transferPhotos;
    }

    public void setTransferPhotos(List<String> transferPhotos) {
        this.transferPhotos = transferPhotos;
    }

    @Column(nullable = false)
    @ElementCollection(targetClass=String.class)
    public List<String> getFieldPhotos() {
        return fieldPhotos;
    }

    public void setFieldPhotos(List<String> fieldPhotos) {
        this.fieldPhotos = fieldPhotos;
    }

    @Column(nullable = false)
    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }
}
