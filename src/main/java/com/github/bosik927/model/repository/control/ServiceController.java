package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.EntityNotFoundException;
import com.github.bosik927.model.repository.boundary.ServiceRepository;
import com.github.bosik927.model.repository.entity.ServicesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    private static final String SERVICE = "Service";

    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping(path = "/services")
    public Iterable<ServicesEntity> getAll() {
        return serviceRepository.findAll();
    }

    @GetMapping(path = "/services/{id}")
    public ServicesEntity getById(@PathVariable Integer id) throws EntityNotFoundException {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, SERVICE));
    }

    @PostMapping(path = "/services")
    public ServicesEntity add(@RequestBody ServicesEntity serviceEntity) {
        return serviceRepository.save(serviceEntity);
    }

    @PutMapping("/services/{id}")
    public ServicesEntity replace(@RequestBody ServicesEntity newServiceEntity,
                              @PathVariable Integer id) {
        return serviceRepository.findById(id)
                .map(user -> {
                    user.setName(newServiceEntity.getName());
                    user.setPrice(newServiceEntity.getPrice());
                    return serviceRepository.save(newServiceEntity);
                })
                .orElseGet(() -> {
                    newServiceEntity.setServiceId(id);
                    return serviceRepository.save(newServiceEntity);
                });
    }

    @DeleteMapping("/services/{id}")
    void delete(@PathVariable Integer id) {
        serviceRepository.deleteById(id);
    }
}