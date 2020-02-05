package com.randeng.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "income")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_income", allocationSize = 1)
public class Income extends BaseEntity<Long> {
    public enum IncomeType {
        PERSONAL,
        ORGANIZATION
    }
    private Fundraising fundraising;
    private String serialNumber;
    private String source;
    private Date bookingDate;
    private IncomeType incomeType;
    private List<String> photos;
    private BigDecimal amount;
    private Long operatorId;

    @ManyToOne(optional = true)
    @JoinColumn(name="fundraising_id")
    public Fundraising getFundraising() {
        return fundraising;
    }

    public void setFundraising(Fundraising fundraising) {
        this.fundraising = fundraising;
    }

    @Column(nullable = false)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Column(nullable = false)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Column(nullable = false)
    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

    @Column
    @ElementCollection(targetClass=String.class)
    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @Column(nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(nullable = false)
    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}
