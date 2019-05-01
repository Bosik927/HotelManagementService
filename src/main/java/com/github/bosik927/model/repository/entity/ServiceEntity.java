package com.github.bosik927.model.repository.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "service", schema = "hotel_db", catalog = "")
public class ServiceEntity {

    private int serviceId;
    private String name;
    private Double price;
    private AccountTypeEntity accountTypeByAvailabilityId;

    @Id
    @Column(name = "serviceId", nullable = false)
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceEntity that = (ServiceEntity) o;

        if (serviceId != that.serviceId) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        int result = serviceId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "availabilityId", referencedColumnName = "accountId", nullable = false)
    public AccountTypeEntity getAccountTypeByAvailabilityId() {
        return accountTypeByAvailabilityId;
    }

    public void setAccountTypeByAvailabilityId(AccountTypeEntity accounttypesByAvailabilityId) {
        this.accountTypeByAvailabilityId = accounttypesByAvailabilityId;
    }
}