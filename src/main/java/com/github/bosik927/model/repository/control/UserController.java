package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.EntityNotFoundException;
import com.github.bosik927.model.repository.boundary.UserRepository;
import com.github.bosik927.model.repository.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final String USER = "User";

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/users")
    public Iterable<UsersEntity> getAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public UsersEntity getById(@PathVariable Integer id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, USER));
    }

    @PostMapping(path = "/users")
    public UsersEntity add(@RequestBody UsersEntity accountType) {
        return userRepository.save(accountType);
    }

    @PutMapping("/users/{id}")
    public UsersEntity replace(@RequestBody UsersEntity newUserEntity,
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