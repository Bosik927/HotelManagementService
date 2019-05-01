package com.github.bosik927.model.repository.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "accounttypes", schema = "hotel_db", catalog = "")
public class AccountTypeEntity {

    private int accountId;
    private String type;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "accountId", nullable = false)
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountTypeEntity that = (AccountTypeEntity) o;

        if (accountId != that.accountId) return false;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        int result = accountId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}