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
    private String username;

    /**
     * User's name.
     */
    private String name;

    /**
     * The plain text password.
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
