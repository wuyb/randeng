package com.randeng.api.controller.dto.user;

/**
 * The login request DTO.
 */
public class LoginRequest {

    /**
     * The mobile number.
     */
    private String mobile;

    /**
     * The password.
     */
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
