package com.github.bosik927.model.repository.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders", schema = "hotel_db", catalog = "")
public class OrdersEntity {
    private int orderId;
    private int userId;
    private Timestamp data;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "data")
    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (orderId != that.orderId) return false;
        if (userId != that.userId) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + userId;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
