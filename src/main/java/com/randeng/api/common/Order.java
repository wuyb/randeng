package com.randeng.api.common;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The <code>Order</code> class is used in queries for sorting.
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 306666204696750371L;

    /**
     * The direction definition.
     */
    public enum Direction {

        asc,

        desc
    }

    private static final Order.Direction DEFAULT_DIRECTION = Order.Direction.desc;

    /**
     * The property to sort on.
     */
    private String property;

    /**
     * The direction of the sorting. The default value is <code>desc</code>.
     */
    private Order.Direction direction = DEFAULT_DIRECTION;

    public Order() {
    }

    /**
     * Creates an <code>Order</code> with property and direction.
     * @param property the property
     * @param direction the direction
     */
    public Order(String property, Order.Direction direction) {
        this.property = property;
        this.direction = direction;
    }

    /**
     * Creates an <code>Order</code> with <code>asc</code> direction.
     * @param property the property to sort on
     * @return the created instance
     */
    public static Order asc(String property) {
        return new Order(property, Order.Direction.asc);
    }

    /**
     * Creates an <code>Order</code> with <code>desc</code> direction.
     * @param property the property to sort on
     * @return the created instance
     */
    public static Order desc(String property) {
        return new Order(property, Order.Direction.desc);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Order.Direction getDirection() {
        return direction;
    }

    public void setDirection(Order.Direction direction) {
        this.direction = direction;
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
        Order other = (Order) obj;
        return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getDirection(), other.getDirection()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getDirection()).toHashCode();
    }

}