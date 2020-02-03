package com.randeng.api.persistence.impl;

import com.randeng.api.model.Role;
import com.randeng.api.persistence.RoleDao;
import org.springframework.stereotype.Repository;

@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {
}
