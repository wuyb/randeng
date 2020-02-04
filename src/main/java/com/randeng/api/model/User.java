package com.randeng.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * The user entity represents a user-end user who has access to the system through wechat.
 */
@Entity
@Table(name = "user")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_user", allocationSize = 1)
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = -3497825743077481879L;

    /**
     * The username used for login.
     */
    private String username;

    /**
     * The hashed password.
     */
    private String password;

    /**
     * The name of the user, it is used for display in the system.
     */
    private String name;

    /**
     * The mobile number for the user.
     */
    private String mobile;

    /**
     * The roles of the user.
     */
    private List<Role> roles;

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Column
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
