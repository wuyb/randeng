package com.randeng.api.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>Pageable</code> class defines a pagination request.
 *
 * <h2>About the page number:</h2>
 * The page number starts from <b>one</b>, instead of zero.
 * So when it is set to be less than one, the value stays as before.
 *
 * <h2>About the page size: </h2>
 * The page request restrains the size of the page to be [1, 1000].
 * If the page size is set to be out of the range, the value is not used and the page size stays as before.
 */
public class Pageable implements Serializable {

    private static final long serialVersionUID = -2219681229828528267L;

    private static final int DEFAULT_PAGE_NUMBER = 1;

    private static final int DEFAULT_PAGE_SIZE = 20;

    private static final int MAX_PAGE_SIZE = 1000;

    /**
     * The page number, which starts from 1. Default value is 1.
     */
    private int pageNumber = DEFAULT_PAGE_NUMBER;

    /**
     * The page size. Default value is 20.
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * The property to search on.
     */
    private String searchProperty;

    /**
     * The value of the search key.
     */
    private String searchValue;

    /**
     * The property to sort on.
     */
    private String orderProperty;

    /**
     * The sorting direction.
     */
    private Order.Direction orderDirection;

    /**
     * The filters.
     */
    private List<Filter> filters = new ArrayList<Filter>();

    /**
     * The sorting orders.
     */
    private List<Order> orders = new ArrayList<Order>();

    public Pageable() {
    }

    /**
     * Creates a <code>Pageable</code> instance with page number and page size.
     * @param pageNumber the page number
     * @param pageSize the page size
     */
    public Pageable(Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageNumber >= 1) {
            this.pageNumber = pageNumber;
        }
        if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
            this.pageSize = pageSize;
        }
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public String getSearchProperty() {
        return searchProperty;
    }

    public void setSearchProperty(String searchProperty) {
        this.searchProperty = searchProperty;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getOrderProperty() {
        return orderProperty;
    }

    public void setOrderProperty(String orderProperty) {
        this.orderProperty = orderProperty;
    }

    public Order.Direction getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(Order.Direction orderDirection) {
        this.orderDirection = orderDirection;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Pageable other = (Pageable) obj;
        return new EqualsBuilder().append(getPageNumber(), other.getPageNumber()).append(getPageSize(), other.getPageSize()).append(getSearchProperty(), other.getSearchProperty()).append(getSearchValue(), other.getSearchValue()).append(getOrderProperty(), other.getOrderProperty())
                .append(getOrderDirection(), other.getOrderDirection()).append(getFilters(), other.getFilters()).append(getOrders(), other.getOrders()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPageNumber()).append(getPageSize()).append(getSearchProperty()).append(getSearchValue()).append(getOrderProperty()).append(getOrderDirection()).append(getFilters()).append(getOrders()).toHashCode();
    }

}