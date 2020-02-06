package com.randeng.api.service.impl;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.Role;
import com.randeng.api.model.User;
import com.randeng.api.persistence.RoleDao;
import com.randeng.api.persistence.UserDao;
import com.randeng.api.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    @Resource(name = "roleDaoImpl")
    private RoleDao roleDao;

    @Override
    public User findByUsername(String username) {
        List<User> users = userDao.findList(null, null, Arrays.asList(new Filter("username", Filter.Operator.eq, username)), null);
        Assert.isTrue(users.size() <= 1, "There should only be at most one user with the same username : " + username);
        if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public Page<User> findByRole(String roleName, Pageable pageable) {
        List<Role> roles = roleDao.findList(null, null, Arrays.asList(new Filter("name", Filter.Operator.eq, roleName)), null);
        Assert.isTrue(roles.size() <= 1, "There should only be at most one role with the same name : " + roleName);
        if (roles.size() == 0) {
            throw new RuntimeException("The role " + roleName + " cannot be found.");
        }
        Role role = roles.get(0);
        return userDao.findByRoleInPage(role, pageable);
    }

    @Override
    public Long getPosition(User user) {
        return userDao.getPosition(user);
    }
}
