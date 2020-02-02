package com.randeng.api.model;

import org.apache.commons.lang3.builder.CompareToBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * The base class for all sortable entities. The order column is indexable.
 * @param <ID> The type of the entities' id.
 */
@MappedSuperclass
public abstract class OrderEntity<ID extends Serializable> extends BaseEntity<ID> implements Comparable<OrderEntity<ID>> {

    private static final long serialVersionUID = 4258831831582429715L;

    /**
     * The name of the order property.
     */
    public static final String ORDER_PROPERTY_NAME = "order";

    /**
     * The order value.
     */
    private Integer order;

    @Min(0)
    @Column(name = "orders")
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public int compareTo(OrderEntity<ID> orderEntity) {
        if (orderEntity == null) {
            return 1;
        }
        return new CompareToBuilder().append(getOrder(), orderEntity.getOrder()).append(getId(), orderEntity.getId()).toComparison();
    }

}
