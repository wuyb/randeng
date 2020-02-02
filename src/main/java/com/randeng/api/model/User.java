package com.randeng.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The user entity represents a business owner who has access to the system.
 */
@Entity
@Table(name = "user")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_user")
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = -3497825743077481879L;

    /**
     * The mobile number for the user. This is also the login username.
     */
    private String mobile;

    /**
     * The hashed password.
     */
    private String password;

    /**
     * The name of the user, it is used for display in the system.
     */
    private String name;

    @Column(nullable = false)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(nullable = false)
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
