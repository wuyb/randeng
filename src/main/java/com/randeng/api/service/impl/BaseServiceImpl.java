package com.randeng.api.service.impl;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Order;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.BaseEntity;
import com.randeng.api.persistence.BaseDao;
import com.randeng.api.service.BaseService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseService<T, ID> {

    private static final String[] UPDATE_IGNORE_PROPERTIES = new String[] {
            BaseEntity.CREATE_DATE_PROPERTY_NAME,
            BaseEntity.MODIFY_DATE_PROPERTY_NAME,
            BaseEntity.VERSION_PROPERTY_NAME
    };

    private BaseDao<T, ID> baseDao;

    @Autowired
    protected void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    @Transactional(readOnly = true)
    public T find(ID id) {
        return baseDao.find(id);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return baseDao.findList(null, null, null, null);
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Boolean includesDeleted) {
        return baseDao.findList(null, null, null, null, includesDeleted);
    }

    @Transactional(readOnly = true)
    public List<T> findList(ID... ids) {
        List<T> result = new ArrayList<T>();
        if (ids != null) {
            for (ID id : ids) {
                T entity = find(id);
                if (entity != null) {
                    result.add(entity);
                }
            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<T> findList(Integer count, List<Filter> filters, List<Order> orders) {
        return findList(null, count, filters, orders);
    }

    @Transactional(readOnly = true)
    public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) {
        return baseDao.findList(first, count, filters, orders);
    }

    @Transactional(readOnly = true)
    public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders, Boolean includesDeleted) {
        return baseDao.findList(first, count, filters, orders, includesDeleted);
    }

    @Transactional(readOnly = true)
    public Page<T> findPage(Pageable pageable) {
        return baseDao.findPage(pageable);
    }

    @Transactional(readOnly = true)
    public Page<T> findPage(Pageable pageable, Boolean includesDeleted) {
        return baseDao.findPage(pageable, includesDeleted);
    }

    @Transactional(readOnly = true)
    public long count() {
        return count(new Filter[] {});
    }

    @Transactional(readOnly = true)
    public long count(Boolean includesDeleted) {
        return baseDao.count(new Filter[] {});
    }

    @Transactional(readOnly = true)
    public long count(Filter... filters) {
        return baseDao.count(filters);
    }

    @Transactional(readOnly = true)
    public boolean exists(ID id) {
        T entity = baseDao.find(id);
        return baseDao.find(id) != null && Boolean.FALSE == entity.isDeleted();
    }

    @Transactional(readOnly = true)
    public boolean exists(Filter... filters) {
        List<Filter> filterList = new ArrayList<>(Arrays.asList(filters));
        filterList.add(new Filter(BaseEntity.DELETED_PROPERTY_NAME, Filter.Operator.eq, false));
        return baseDao.count(filterList.toArray(new Filter[filterList.size()])) > 0;
    }

    @Transactional
    public T save(T entity) {
        Assert.notNull(entity, "Entity should not be null.");
        Assert.isTrue(entity.isNew(), "Entity must be new.");

        baseDao.persist(entity);
        return entity;
    }

    @Transactional
    public T update(T entity) {
        Assert.notNull(entity, "Entity should not be null.");
        Assert.isTrue(!entity.isNew(), "Entity must not be new.");

        if (!baseDao.isManaged(entity)) {
            T persistant = baseDao.find(baseDao.getIdentifier(entity));
            if (persistant != null) {
                copyProperties(entity, persistant, UPDATE_IGNORE_PROPERTIES);
            }
            return persistant;
        }
        return entity;
    }

    @Transactional
    public T update(T entity, String... ignoreProperties) {
        Assert.notNull(entity, "Entity should not be null.");
        Assert.isTrue(!entity.isNew(), "Entity must not be new.");
        Assert.isTrue(!baseDao.isManaged(entity), "Entity must not be managed.");

        T persistant = baseDao.find(baseDao.getIdentifier(entity));
        if (persistant != null) {
            copyProperties(entity, persistant, (String[]) ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
        }
        return update(persistant);
    }

    @Transactional
    public void delete(ID id) {
        delete(baseDao.find(id));
    }

    @Transactional
    public void delete(ID... ids) {
        if (ids != null) {
            for (ID id : ids) {
                delete(baseDao.find(id));
            }
        }
    }

    @Transactional
    public void delete(T entity) {
        if (entity != null) {
            baseDao.remove(baseDao.isManaged(entity) ? entity : baseDao.merge(entity));
        }
    }

    protected void copyProperties(T source, T target, String... ignoreProperties) {
        Assert.notNull(source, "Source should not be null.");
        Assert.notNull(target, "Target should not be null.");

        PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(target);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Method readMethod = propertyDescriptor.getReadMethod();
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (ArrayUtils.contains(ignoreProperties, propertyName)
                    || readMethod == null
                    || writeMethod == null
                    || !baseDao.isLoaded(source, propertyName)) {
                continue;
            }
            try {
                Object sourceValue = readMethod.invoke(source);
                writeMethod.invoke(target, sourceValue);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

}