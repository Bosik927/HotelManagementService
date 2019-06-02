package com.github.bosik927.model.repository.entity;

import java.util.List;

public class Order {

    private int userId;
    private List<Integer> services;

    public List<Integer> getServices() {
        return services;
    }

    public void setServices(List<Integer> services) {
        this.services = services;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
