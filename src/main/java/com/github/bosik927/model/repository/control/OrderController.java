package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.EntityNotFoundException;
import com.github.bosik927.model.repository.boundary.OrderRepository;
import com.github.bosik927.model.repository.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private static final String ORDER = "Order";

    @Autowired
    OrderRepository orderRepository;

    @GetMapping(path = "/orders")
    public Iterable<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    @GetMapping(path = "/orders/{id}")
    public OrderEntity getById(@PathVariable Integer id) throws EntityNotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, ORDER));
    }

    @PostMapping(path = "/orders")
    public OrderEntity add(@RequestBody OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @PutMapping("/orders/{id}")
    public OrderEntity replace(@RequestBody OrderEntity newOrderEntity,
                                 @PathVariable Integer id) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setData(newOrderEntity.getData());
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    newOrderEntity.setOrderId(id);
                    return orderRepository.save(newOrderEntity);
                });
    }

    @DeleteMapping("/orders/{id}")
    void delete(@PathVariable Integer id) {
        orderRepository.deleteById(id);
    }
}