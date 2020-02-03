package com.randeng.api.service;

import com.randeng.api.model.Role;

/**
 * This interface defines the role service.
 */
public interface RoleService extends BaseService<Role, Long> {
    /**
     * Finds role by name.
     * @param name the name
     * @return the role, if not found, null shall be returned
     */
    Role findByName(String name);
}
