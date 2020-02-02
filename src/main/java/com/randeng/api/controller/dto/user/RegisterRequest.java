package com.randeng.api.controller.dto.user;

import java.io.Serializable;

/**
 * The register request DTO.
 */
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 2497774096591434380L;

    /**
     * The mobile number.
     */
    private String mobile;

    /**
     * User's name.
     */
    private String name;

    /**
     * The plain text password.
     */
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
