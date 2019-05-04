package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.EntityNotFoundException;
import com.github.bosik927.model.repository.boundary.AccountTypeRepository;
import com.github.bosik927.model.repository.entity.AccountTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountTypeController {

    private static final String ACCOUNT_TYPE = "AccountType";

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @GetMapping(path = "/accountTypes")
    public Iterable<AccountTypeEntity> getAll() {
        return accountTypeRepository.findAll();
    }

    @GetMapping(path = "/accountTypes/{id}")
    public AccountTypeEntity getById(@PathVariable Integer id) throws EntityNotFoundException {
        return accountTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id,ACCOUNT_TYPE));
    }

    @PostMapping(path = "/accountTypes")
    public AccountTypeEntity add(@RequestBody AccountTypeEntity accountType) {
        return accountTypeRepository.save(accountType);
    }

    @PutMapping("/accountTypes/{id}")
    public AccountTypeEntity replace(@RequestBody AccountTypeEntity newAccountTypeEntity,
                                     @PathVariable Integer id) {
        return accountTypeRepository.findById(id)
                .map(typeEntity -> {
                    typeEntity.setType(newAccountTypeEntity.getType());
                    return accountTypeRepository.save(typeEntity);
                })
                .orElseGet(() -> {
                    newAccountTypeEntity.setAccountId(id);
                    return accountTypeRepository.save(newAccountTypeEntity);
                });
    }

    @DeleteMapping("/accountTypes/{id}")
    public void delete(@PathVariable Integer id) {
       accountTypeRepository.deleteById(id);
    }
}