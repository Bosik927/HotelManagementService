package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.EntityNotFoundException;
import com.github.bosik927.model.repository.boundary.OrderRepository;
import com.github.bosik927.model.repository.boundary.OrderServiceRepository;
import com.github.bosik927.model.repository.boundary.ServiceRepository;
import com.github.bosik927.model.repository.entity.Order;
import com.github.bosik927.model.repository.entity.OrderServiceEntity;
import com.github.bosik927.model.repository.entity.OrdersEntity;
import com.github.bosik927.model.repository.entity.ServicesEntity;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    private static final String ORDER = "Order";

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderServiceRepository orderServiceRepository;
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping(path = "/orders")
    public Iterable<OrdersEntity> getAll() {
        return orderRepository.findAll();
    }

    @GetMapping(path = "/orders/service/{orderId}")
    public List<ServicesEntity> getOrderServices(@PathVariable Integer orderId) throws EntityNotFoundException {
        Iterable<OrderServiceEntity> source = orderServiceRepository.findAll();
        List<OrderServiceEntity> target = new ArrayList<>();
        source.forEach(target::add);

        List<Integer> servicesIds = target.stream().filter(orderService -> orderService.getOrderId() == orderId)
                .map(OrderServiceEntity::getServiceId)
                .collect(Collectors.toList());

        List<ServicesEntity> services = new ArrayList<>();
        servicesIds.forEach(id -> services.add(serviceRepository.findById(id).get()));
        return services;
    }

    @GetMapping(path = "/orders/user{userId}")
    public List<OrdersEntity> getByUserId(@PathVariable Integer userId){
        Iterable<OrdersEntity> source = orderRepository.findAll();
        List<OrdersEntity> target = new ArrayList<>();
        source.forEach(target::add);

        return target.stream().filter(order->order.getUserId()==userId)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/orders/{id}")
    public OrdersEntity getById(@PathVariable Integer id) throws EntityNotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, ORDER));
    }

    @PostMapping(path = "/orders")
    public String add(@RequestBody String json) {
        Gson gson = new Gson();
        Order order = gson.fromJson(json, Order.class);

        Timestamp now = new Timestamp(System.currentTimeMillis());

        OrdersEntity orderEntity = new OrdersEntity();
        orderEntity.setUserId(order.getUserId());
        orderEntity.setData(now);

        orderRepository.save(orderEntity);
        Iterable<OrdersEntity> orders = orderRepository.findAll();

        OrdersEntity lastElement = null;

        for (OrdersEntity element : orders) {
            lastElement = element;
        }

        Iterable<Integer> services = order.getServices();

        for (Integer id : services){
            OrderServiceEntity orderServiceEntity = new OrderServiceEntity();
            orderServiceEntity.setServiceId(id);
            orderServiceEntity.setOrderId(lastElement.getOrderId());
            orderServiceRepository.save(orderServiceEntity);
        }

        return "Done";
    }

    @PutMapping("/orders/{id}")
    public OrdersEntity replace(@RequestBody OrdersEntity newOrderEntity,
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