package com.github.bosik927.model.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_service", schema = "hotel_db", catalog = "")
public class OrderServiceEntity {
    private int orderServiceId;
    private int orderId;
    private int serviceId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_service_id")
    public int getOrderServiceId() {
        return orderServiceId;
    }

    public void setOrderServiceId(int orderServiceId) {
        this.orderServiceId = orderServiceId;
    }

    @Basic
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "service_id")
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderServiceEntity that = (OrderServiceEntity) o;

        if (orderServiceId != that.orderServiceId) return false;
        if (orderId != that.orderId) return false;
        if (serviceId != that.serviceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderServiceId;
        result = 31 * result + orderId;
        result = 31 * result + serviceId;
        return result;
    }
}
