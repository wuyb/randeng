package com.randeng.api.controller.dto;

import java.math.BigDecimal;

public class UserDonationStatsResponse {
    private Long count;
    private BigDecimal amount;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
