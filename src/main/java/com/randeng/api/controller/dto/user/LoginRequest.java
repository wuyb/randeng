package com.randeng.api.controller.dto.user;

/**
 * The login request DTO.
 */
public class LoginRequest {

    /**
     * The mobile number.
     */
    private String username;

    /**
     * The password.
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
