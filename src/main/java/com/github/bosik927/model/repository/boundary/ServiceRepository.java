package com.github.bosik927.model.repository.boundary;

import com.github.bosik927.model.repository.entity.ServiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<ServiceEntity, Integer> {
}