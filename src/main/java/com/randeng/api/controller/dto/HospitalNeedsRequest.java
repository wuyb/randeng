package com.randeng.api.controller.dto;

public class HospitalNeedsRequest {
    private Long state;
    private String remarks;

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
