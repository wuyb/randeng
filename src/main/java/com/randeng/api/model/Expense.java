package com.randeng.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "expense")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_expense", allocationSize = 1)
public class Expense extends BaseEntity<Long> {
    public enum ExpenseType {
        LOGISTIC,
        PURCHASE,
        REFUND
    }
    private Fundraising fundraising;
    private String serialNumber;
    private Date bookingDate;
    private ExpenseType expenseType;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Column(nullable = false)
    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
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
