package com.randeng.api.service;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.User;

/**
 * This interface defines the user service.
 */
public interface UserService extends BaseService<User, Long> {
    /**
     * Finds user by username.
     * @param username the username
     * @return the user, if not found, null shall be returned
     */
    User findByUsername(String username);

    /**
     * Finds users by role.
     * @param roleName the name of the role
     * @param pageable the pageable request includes page number and size
     * @return a page object
     */
    Page<User> findByRole(String roleName, Pageable pageable);

    Long getPosition(User currentUser);
}
