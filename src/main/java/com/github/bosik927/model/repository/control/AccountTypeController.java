package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.AccountTypeRepository;
import com.github.bosik927.model.repository.entity.AccountTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static com.github.bosik927.model.repository.control.AccountTypeController.ACCOUNT_TYPE_BASIC_PATH;

@Controller
@RequestMapping(path = ACCOUNT_TYPE_BASIC_PATH)
public class AccountTypeController {

    public static final String ACCOUNT_TYPE_BASIC_PATH = "/accountType";

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @GetMapping(path ="/add")
    public @ResponseBody
    String addNewAccountType(@RequestParam String name) {
        AccountTypeEntity accountType = new AccountTypeEntity();
        accountType.setType(name);
        accountTypeRepository.save(accountType);
        return "SAVED";
    }

    @GetMapping(path = "/getAll")
    public @ResponseBody
    Iterable<AccountTypeEntity> getAllUsers() {
        return accountTypeRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody
    Optional<AccountTypeEntity> getById(@RequestParam Integer id) {
        return accountTypeRepository.findById(id);
    }
}