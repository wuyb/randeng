package com.randeng.api.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the page definition.
 * @param <T> the type of the objects in the page.
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1776227205224688802L;

    /**
     * The list of paged objects.
     */
    private final List<T> content = new ArrayList<T>();

    /**
     * The total number of the querying result. It is not the number of objects in <code>content</code>.
     */
    private final long total;

    /**
     * The pagination request.
     */
    private final Pageable pageable;

    public Page() {
        this.total = 0L;
        this.pageable = new Pageable();
    }

    /**
     * Creates a <code>Page</code> with content, total number and the paging information.
     * @param content the content
     * @param total the total number of objects
     * @param pageable the paging information
     */
    public Page(List<T> content, long total, Pageable pageable) {
        this.content.addAll(content);
        this.total = total;
        this.pageable = pageable;
    }

    public int getPageNumber() {
        return pageable.getPageNumber();
    }

    public int getPageSize() {
        return pageable.getPageSize();
    }

    public String getSearchProperty() {
        return pageable.getSearchProperty();
    }

    public String getSearchValue() {
        return pageable.getSearchValue();
    }

    public String getOrderProperty() {
        return pageable.getOrderProperty();
    }

    public Order.Direction getOrderDirection() {
        return pageable.getOrderDirection();
    }

    public List<Order> getOrders() {
        return pageable.getOrders();
    }

    public List<Filter> getFilters() {
        return pageable.getFilters();
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) getTotal() / (double) getPageSize());
    }

    public List<T> getContent() {
        return content;
    }

    public long getTotal() {
        return total;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public static final <T> Page<T> emptyPage(Pageable pageable) {
        return new Page<T>(Collections.<T> emptyList(), 0L, pageable);
    }

}