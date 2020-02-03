package com.randeng.api.persistence.impl;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.BaseEntity;
import com.randeng.api.model.Role;
import com.randeng.api.model.User;
import com.randeng.api.persistence.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {
    @Override
    public Page<User> findByRoleInPage(Role role, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root)
            .where(criteriaBuilder.isMember(role, root.get("roles")));

        if (pageable.getFilters() == null) {
            pageable.setFilters(new ArrayList<>());
        } else {
            pageable.setFilters(new ArrayList<>(pageable.getFilters()));
        }
        pageable.getFilters().add(new Filter(BaseEntity.DELETED_PROPERTY_NAME, Filter.Operator.eq, false));

        return findPage(criteriaQuery, pageable);
    }
}
