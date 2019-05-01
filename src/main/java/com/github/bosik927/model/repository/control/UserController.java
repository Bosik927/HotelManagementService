package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.UserRepository;
import com.github.bosik927.model.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static com.github.bosik927.model.repository.control.UserController.USER_BASIC_PATH;

@Controller
@RequestMapping(path = USER_BASIC_PATH)
public class UserController {

    public static final String USER_BASIC_PATH = "/user";

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path ="/add")
    public @ResponseBody
    String addNewOrder() {
        UserEntity userEntity = new UserEntity();
//        userEntity.setAccounttypesByAccountTypeId();
//        userEntity.setCardNumber();
//        userEntity.setName();
//        userEntity.setSurname();
        userRepository.save(userEntity);
        return "SAVED";
    }

    @GetMapping(path = "/getAll")
    public @ResponseBody
    Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody
    Optional<UserEntity> getById(@RequestParam Integer id) {
        return userRepository.findById(id);
    }
}
