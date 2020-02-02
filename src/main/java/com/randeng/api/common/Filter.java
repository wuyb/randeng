package com.randeng.api.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * The <code>Filter</code> class is used in queries for constraining.
 */
public class Filter implements Serializable {

    private static final long serialVersionUID = -8644704654980903960L;

    /**
     * The filter operator.
     */
    public enum Operator {

        eq,

        ne,

        gt,

        lt,

        ge,

        le,

        like,

        in,

        isNull,

        isNotNull
    }

    private static final boolean DEFAULT_IGNORE_CASE = false;

    /**
     * The property to filter on.
     */
    private String property;

    /**
     * The filter operator.
     */
    private Filter.Operator operator;

    /**
     * The value to filter on.
     */
    private Object value;

    /**
     * Whether the case is ignored. This applies to character/string filters only.
     * The default value is false.
     */
    private Boolean ignoreCase = DEFAULT_IGNORE_CASE;

    public Filter() {
    }

    /**
     * Creates a filter with the property, operator and the filter value.
     * @param property the name of the property
     * @param operator the operator
     * @param value the filter value
     */
    public Filter(String property, Filter.Operator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    /**
     * Creates a filter with the property, operator and the filter value.
     * @param property the name of the property
     * @param operator the operator
     * @param value the filter value
     * @param ignoreCase whether to ignore case
     */
    public Filter(String property, Filter.Operator operator, Object value, boolean ignoreCase) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = ignoreCase;
    }

    /**
     * Creates a filter with <code>eq</code> operator.
     * @param property the name of the property
     * @param value the filter value
     * @return the created filter
     */
    public static Filter eq(String property, Object value) {
        return new Filter(property, Filter.Operator.eq, value);
    }

    /**
     * Creates a filter with <code>eq</code> operator with ignore case flag.
     * @param property the name of the property
     * @param value the filter value
     * @param ignoreCase whether to ignore case
     * @return the created filter
     */
    public static Filter eq(String property, Object value, boolean ignoreCase) {
        return new Filter(property, Filter.Operator.eq, value, ignoreCase);
    }

    /**
     * Creates a filter with <code>nq</code> (not equal) operator.
     * @param property the name of the property
     * @param value the filter value
     * @return the created filter
     */
    public static Filter ne(String property, Object value) {
        return new Filter(property, Filter.Operator.ne, value);
    }

    /**
     * Creates a filter with <code>nq</code> (not equal) operator with ignore case flag.
     * @param property the name of the property
     * @param value the filter value
     * @param ignoreCase whether to ignore case
     * @return the created filter
     */
    public static Filter ne(String property, Object value, boolean ignoreCase) {
        return new Filter(property, Filter.Operator.ne, value, ignoreCase);
    }

    /**
     * Creates a filter with <code>gt</code> (greater than) operator.
     * @param property the name of the property
     * @param value the filter value
     * @return the created filter
     */
    public static Filter gt(String property, Object value) {
        return new Filter(property, Filter.Operator.gt, value);
    }

    /**
     * Creates a filter with <code>lt</code> (less than) operator.
     * @param property the name of the property
     * @param value the filter value
     * @return the created filter
     */
    public static Filter lt(String property, Object value) {
        return new Filter(property, Filter.Operator.lt, value);
    }

    /**
     * Creates a filter with <code>ge</code> (greater than or equal to) operator.
     * @param property the name of the property
     * @param value the filter value
     * @return the created filter
     */
    public static Filter ge(String property, Object value) {
        return new Filter(property, Filter.Operator.ge, value);
    }

    /**
     * Creates a filter with <code>le</code> (less than or equal to) operator.
     * @param property the name of the property
     * @param value the filter value
     * @return the created filter
     */
    public static Filter le(String property, Object value) {
        return new Filter(property, Filter.Operator.le, value);
    }

    /**
     * Creates a filter with <code>like</code> operator.
     * @param property the name of the property
     * @param value the filter value
     * @return the created filter
     */
    public static Filter like(String property, Object value) {
        return new Filter(property, Filter.Operator.like, value);
    }

    /**
     * Creates a filter with <code>in</code> operator.
     * @param property the name of the property
     * @param value the filter value
     * @return the created filter
     */
    public static Filter in(String property, Object value) {
        return new Filter(property, Filter.Operator.in, value);
    }

    /**
     * Creates a filter with <code>isNull</code> operator.
     * @param property the name of the property
     * @return the created filter
     */
    public static Filter isNull(String property) {
        return new Filter(property, Filter.Operator.isNull, null);
    }

    /**
     * Creates a filter with <code>isNotNull</code> operator.
     * @param property the name of the property
     * @return the created filter
     */
    public static Filter isNotNull(String property) {
        return new Filter(property, Filter.Operator.isNotNull, null);
    }

    /**
     * Returns the same filter but with <code>ignoreCase</code> set to true.
     * @return the same filter
     */
    public Filter ignoreCase() {
        this.ignoreCase = true;
        return this;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Filter.Operator getOperator() {
        return operator;
    }

    public void setOperator(Filter.Operator operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean getIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
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
        Filter other = (Filter) obj;
        return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getOperator(), other.getOperator()).append(getValue(), other.getValue()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
    }

}