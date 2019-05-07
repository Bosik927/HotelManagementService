package com.github.bosik927.model.repository.boundary;

import com.github.bosik927.model.repository.entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UsersEntity, Integer> {
}