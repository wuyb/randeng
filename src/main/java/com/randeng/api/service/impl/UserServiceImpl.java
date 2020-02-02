package com.randeng.api.service.impl;

import com.randeng.api.common.Filter;
import com.randeng.api.model.User;
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

    @Override
    public User findByMobile(String mobile) {
        List<User> users = userDao.findList(null, null, Arrays.asList(new Filter("mobile", Filter.Operator.eq, mobile)), null);
        Assert.isTrue(users.size() <= 1, "There should only be at most one user with the same mobile : " + mobile);
        if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }
}
