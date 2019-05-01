package com.github.bosik927.model.repository.boundary;

import com.github.bosik927.model.repository.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
}