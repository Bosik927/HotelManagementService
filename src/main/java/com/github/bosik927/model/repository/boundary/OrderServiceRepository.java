package com.github.bosik927.model.repository.boundary;

import com.github.bosik927.model.repository.entity.OrderServiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderServiceRepository extends CrudRepository<OrderServiceEntity, Integer> {
}