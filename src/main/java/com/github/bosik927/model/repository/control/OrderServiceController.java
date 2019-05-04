package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.EntityNotFoundException;
import com.github.bosik927.model.repository.boundary.OrderServiceRepository;
import com.github.bosik927.model.repository.entity.OrderServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderServiceController {

    private static final String ORDER_SERVICE = "OrderService";

    @Autowired
    OrderServiceRepository orderServiceRepository;

    @GetMapping(path = "/orderService")
    public Iterable<OrderServiceEntity> getAll() {
        return orderServiceRepository.findAll();
    }

    @GetMapping(path = "/orderService/{id}")
    public OrderServiceEntity getById(@PathVariable Integer id) throws EntityNotFoundException {
        return orderServiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, ORDER_SERVICE));
    }

    @PostMapping(path = "/orderService")
    public OrderServiceEntity add(@RequestBody OrderServiceEntity orderServiceEntity) {
        return orderServiceRepository.save(orderServiceEntity);
    }

    @PutMapping("/orderService/{id}")
    public OrderServiceEntity replace(@RequestBody OrderServiceEntity newOrderServiceEntity,
                               @PathVariable Integer id) {
        return orderServiceRepository.findById(id)
                .map(orderService -> {
                    orderService.setOrdersByOrderId(newOrderServiceEntity.getOrdersByOrderId());
                    return orderServiceRepository.save(orderService);
                })
                .orElseGet(() -> {
                    newOrderServiceEntity.setOrderServiceId(id);
                    return orderServiceRepository.save(newOrderServiceEntity);
                });
    }

    @DeleteMapping("/orderService/{id}")
    void delete(@PathVariable Integer id) {
        orderServiceRepository.deleteById(id);
    }
}