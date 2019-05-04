package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.AccountTypeRepository;
import com.github.bosik927.model.repository.boundary.EntityNotFoundException;
import com.github.bosik927.model.repository.boundary.UserRepository;
import com.github.bosik927.model.repository.entity.AccountTypeEntity;
import com.github.bosik927.model.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private static final String USER = "User";

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/users")
    public Iterable<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public UserEntity getById(@PathVariable Integer id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, USER));
    }

    @PostMapping(path = "/users")
    public UserEntity add(@RequestBody UserEntity accountType) {
        return userRepository.save(accountType);
    }

    @PutMapping("/users/{id}")
    public UserEntity replace(@RequestBody UserEntity newUserEntity,
                              @PathVariable Integer id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUserEntity.getName());
                    return userRepository.save(newUserEntity);
                })
                .orElseGet(() -> {
                    newUserEntity.setUserId(id);
                    return userRepository.save(newUserEntity);
                });
    }

    @DeleteMapping("/users/{id}")
    void delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}