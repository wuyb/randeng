package com.randeng.api.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This entity is an implementation of the Spring Security <code>UserDetails</code> interface.
 */
public class YeahUserDetails implements UserDetails {

    private static final long serialVersionUID = 1823647689397383942L;

    /**
     * User name.
     */
    private String username;

    /**
     * Password.
     */
    private String password;

    /**
     * Flag of whether the account has expired. Always return true.
     */
    private Boolean isAccountNonExpired = true;

    /**
     * Flag of whether the account has been locked. Always return true.
     */
    private Boolean isAccountNonLocked = true;

    /**
     * Flag of whether the account's credentials has been locked. Always return true.
     */
    private Boolean isCredentialsNonExpired = true;

    /**
     * Flag of whether the account has been enabled. Always return true.
     */
    private Boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
