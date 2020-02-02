package com.randeng.api.service;

import com.randeng.api.common.Filter;
import com.randeng.api.common.Order;
import com.randeng.api.common.Page;
import com.randeng.api.common.Pageable;
import com.randeng.api.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * This interface defines the specification of services.
 * @param <T> The entity type.
 * @param <ID> The type of entity id.
 */
public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {

    /**
     * Finds an entity by its id.
     * @param id the id of the entity
     * @return the entity, or null if not found
     */
    T find(ID id);

    /**
     * Gets all entities.
     * @return all entities
     */
    List<T> findAll();

    /**
     * Gets all entities.
     * @param includesDeleted whether to include the deleted entities
     * @return all entities
     */
    List<T> findAll(Boolean includesDeleted);

    /**
     * Finds entities by the give list of ids.
     * @param ids the ids of entities to retrieve
     * @return the list of entities
     */
    List<T> findList(ID... ids);

    /**
     * Finds entities by filters and sorts them by the given orders option.
     * @param count the number of entities to be include
     * @param filters the filters
     * @param orders the sorting options
     * @return the list of entities.
     */
    List<T> findList(Integer count, List<Filter> filters, List<Order> orders);

    /**
     * Finds entities by filters and sorts them by the given orders option.
     * @param count the offset of the first entity to include
     * @param count the number of entities to be include
     * @param filters the filters
     * @param orders the sorting options
     * @return the list of entities.
     */
    List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders);

    /**
     * Finds entities by filters and sorts them by the given orders option.
     * @param count the offset of the first entity to include
     * @param count the number of entities to be include
     * @param filters the filters
     * @param orders the sorting options
     * @param includesDeleted whether to include the deleted entities
     * @return the list of entities.
     */
    List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders, Boolean includesDeleted);

    /**
     * Finds a page of entities by the given pagination option.
     * @param pageable the pagination option
     * @return the page
     */
    Page<T> findPage(Pageable pageable);

    /**
     * Finds a page of entities by the given pagination option.
     * @param pageable the pagination option
     * @param includesDeleted whether to include the deleted entities
     * @return the page
     */
    Page<T> findPage(Pageable pageable, Boolean includesDeleted);

    /**
     * Counts all entities.
     * @return the number of all entities
     */
    long count();

    /**
     * Counts all entities.
     * @param includesDeleted whether to include the deleted entities
     * @return the number of all entities
     */
    long count(Boolean includesDeleted);

    /**
     * Counts the entities that matches the given filters.
     * @param filters the filters
     * @return the number of entities that matches the given filters
     */
    long count(Filter... filters);

    /**
     * Checks whether an entity exists by its id.
     * @param id the id of the entity to check
     * @return true if it exists, otherwise false
     */
    boolean exists(ID id);

    /**
     * Checks whether there exists entity matching the given filters.
     * @param filters the filters
     * @return true if it exists, otherwise false
     */
    boolean exists(Filter... filters);

    /**
     * Saves the give entity.
     * @param entity the entity
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Updates the give entity.
     * @param entity the entity
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Updates the give entity, while ignores the given properties.
     * @param entity the entity
     * @param ignoreProperties the properties to ignore
     * @return the updated entity
     */
    T update(T entity, String... ignoreProperties);

    /**
     * Softly delete the given entity by id.
     * @param id the id of the entity to delete
     */
    void delete(ID id);

    /**
     * Softly delete the given entities by ids.
     * @param ids the ids of the entities to delete
     */
    void delete(ID... ids);

    /**
     * Softly delete the given entity.
     * @param entity the entity to delete
     */
    void delete(T entity);

}