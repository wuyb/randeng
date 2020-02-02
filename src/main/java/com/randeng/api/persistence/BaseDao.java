package com.randeng.api.persistence;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.BaseEntity;
import com.randeng.api.common.Order;

import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;

/**
 * This interface defines the common specification for all DAOs.
 *
 * @param <T> the type of the entity
 * @param <ID> the type of the id for the <code>T</code> entity
 */
public interface BaseDao<T extends BaseEntity<ID>, ID extends Serializable> {

    /**
     * Finds an entity by its id.
     * @param id the id
     * @return the entity
     */
    T find(ID id);

    /**
     * Finds an entity by its id with locking.
     * @param id the id
     * @param lockModeType the lock mode type
     * @return the entity
     */
    T find(ID id, LockModeType lockModeType);

    /**
     * Finds a list of entities by the given conditions.
     * @param first the index of the first entity to return. It can be null, where the list starting from the very first one
     * @param count the maximum number of entities to return. It can be null, where the list contains objects following the given <code>first</code> one
     * @param filters the filters. It can be null.
     * @param orders the sorting options. It can be null.
     * @return a list of objects matching the given conditions.
     */
    List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders);

    /**
     * Finds a list of entities by the given conditions.
     * @param first the index of the first entity to return. It can be null, where the list starting from the very first one
     * @param count the maximum number of entities to return. It can be null, where the list contains objects following the given <code>first</code> one
     * @param filters the filters. It can be null.
     * @param orders the sorting options. It can be null.
     * @param includesDeleted whether to include the deleted ones
     * @return a list of objects matching the given conditions.
     */
    List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders, Boolean includesDeleted);

    /**
     * Finds a page of entities.
     * @param pageable the pagination request
     * @return a page of objects
     */
    Page<T> findPage(Pageable pageable);

    /**
     * Finds a page of entities.
     * @param pageable the pagination request
     * @param includesDeleted whether to include the deleted ones
     * @return a page of objects
     */
    Page<T> findPage(Pageable pageable, Boolean includesDeleted);

    /**
     * Counts the number of entities that matches the given filters
     * @param filters the filters
     * @return the number of entities matching the given filters
     */
    long count(Filter... filters);

    /**
     * Counts the number of entities that matches the given filters
     * @param includesDeleted whether to include the deleted ones
     * @param filters the filters
     * @return the number of entities matching the given filters
     */
    long count(Boolean includesDeleted, Filter... filters);

    /**
     * Saves the given entity.
     * @param entity the entity to save.
     */
    void persist(T entity);

    /**
     * Merges the given entity into persistence context.
     * @param entity the entity to merge
     * @return the merged entity
     */
    T merge(T entity);

    /**
     * Removes the entity from the persistence context.
     * @param entity the entity to remove
     */
    void remove(T entity);

    /**
     * Refreshes the entity from the persistence context.
     * @param entity entity to refresh
     */
    void refresh(T entity);

    /**
     * Refreshes the entity from the persistence context with locking mode type.
     * @param entity entity to refresh
     * @param lockModeType the lock mode type
     */
    void refresh(T entity, LockModeType lockModeType);

    /**
     * Gets the identifier of the given entity.
     * @param entity the entity
     * @return the identifier of the entity
     */
    ID getIdentifier(T entity);

    /**
     * Checks whether all eager attributes have been loaded.
     * @param entity the entity
     * @return whether all eager attributes have been loaded
     */
    boolean isLoaded(T entity);

    /**
     * Checks whether the given attribute have been loaded.
     * @param entity the entity
     * @return whether the given attribute have been loaded
     */
    boolean isLoaded(T entity, String attributeName);

    /**
     * Checks whether the given entity is in the persistence context.
     * @param entity the entity
     * @return whether the given entity is in the persistence context
     */
    boolean isManaged(T entity);

    /**
     * Detach the entity from the persistence context.
     * @param entity the entity
     */
    void detach(T entity);

    /**
     * Gets the lock mode type of the given entity.
     * @param entity the entity
     * @return the lock mode type
     */
    LockModeType getLockMode(T entity);

    /**
     * Locks the given entity.
     * @param entity the entity
     * @param lockModeType the lock mode type
     */
    void lock(T entity, LockModeType lockModeType);

    /**
     * Clears the persistence context by detaching all entities. Changes that haven't been flushed to database will be lost.
     */
    void clear();

    /**
     * Flushes the changes to database.
     */
    void flush();

}