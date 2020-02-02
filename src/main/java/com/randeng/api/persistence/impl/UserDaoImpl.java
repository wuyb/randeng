package com.randeng.api.persistence.impl;

import com.randeng.api.model.User;
import com.randeng.api.persistence.UserDao;
import org.springframework.stereotype.Repository;

@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {
}
