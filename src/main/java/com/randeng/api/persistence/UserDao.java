package com.randeng.api.persistence;

import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.Role;
import com.randeng.api.model.User;

/**
 * The DAO to manage <code>User</code> entity.
 */
public interface UserDao extends BaseDao<User, Long> {
    Page<User> findByRoleInPage(Role role, Pageable pageable);
    Long getPosition(User user);
}
