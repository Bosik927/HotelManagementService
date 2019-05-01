package com.github.bosik927.model.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderservice", schema = "hotel_db", catalog = "")
public class OrderServiceEntity {

    private int orderServiceId;
    private OrderEntity ordersByOrderId;

    @Id
    @Column(name = "OrderServiceId", nullable = false)
    public int getOrderServiceId() {
        return orderServiceId;
    }

    public void setOrderServiceId(int orderServiceId) {
        this.orderServiceId = orderServiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderServiceEntity that = (OrderServiceEntity) o;

        return orderServiceId == that.orderServiceId;
    }

    @Override
    public int hashCode() {
        return orderServiceId;
    }

    @ManyToOne
    @JoinColumn(name = "OrderId", referencedColumnName = "orderId", nullable = false)
    public OrderEntity getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(OrderEntity ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }
}
