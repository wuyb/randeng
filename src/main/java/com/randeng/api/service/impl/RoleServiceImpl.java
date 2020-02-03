package com.randeng.api.service.impl;

import com.randeng.api.common.Filter;
import com.randeng.api.model.Role;
import com.randeng.api.persistence.RoleDao;
import com.randeng.api.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    @Resource(name = "roleDaoImpl")
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        List<Role> roles = roleDao.findList(null, null, Arrays.asList(new Filter("name", Filter.Operator.eq, name)), null);
        Assert.isTrue(roles.size() <= 1, "There should only be at most one role with the same name : " + name);
        if (roles.size() == 0) {
            return null;
        } else {
            return roles.get(0);
        }
    }
}
