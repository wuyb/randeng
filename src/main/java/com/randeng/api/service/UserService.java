package com.randeng.api.service;

import com.randeng.api.model.User;

/**
 * This interface defines the user service.
 */
public interface UserService extends BaseService<User, Long> {
    /**
     * Finds user by mobile number.
     * @param mobile the mobile number
     * @return the user, if not found, null shall be returned
     */
    User findByMobile(String mobile);
}
