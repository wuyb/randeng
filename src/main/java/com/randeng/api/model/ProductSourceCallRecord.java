package com.randeng.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "call_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_call_record", allocationSize = 1)
public class ProductSourceCallRecord extends BaseEntity<Long> {
    private User operator;
    private String comment;
    private ProductSourceCall call;

    @ManyToOne
    @JoinColumn(name="call_id", nullable = false)
    @JsonIgnore
    public ProductSourceCall getCall() {
        return call;
    }

    public void setCall(ProductSourceCall call) {
        this.call = call;
    }

    @ManyToOne
    @JoinColumn(name = "operator_id", nullable = false, updatable = false)
    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }

    @Column(nullable = false)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
