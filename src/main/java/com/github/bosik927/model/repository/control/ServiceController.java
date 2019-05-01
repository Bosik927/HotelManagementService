package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.OrderRepository;
import com.github.bosik927.model.repository.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static com.github.bosik927.model.repository.control.ServiceController.SERVICE_BASIC_PATH;

@Controller
@RequestMapping(path = SERVICE_BASIC_PATH)
public class ServiceController {

    public static final String SERVICE_BASIC_PATH = "/service";

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path ="/add")
    public @ResponseBody
    String addNewOrder() {
        OrderEntity orderEntity = new OrderEntity();
//        orderEntity.setData();
//        orderEntity.setOrderId();
        orderRepository.save(orderEntity);
        return "SAVED";
    }

    @GetMapping(path = "/getAll")
    public @ResponseBody
    Iterable<OrderEntity> getAllUsers() {
        return orderRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody
    Optional<OrderEntity> getById(@RequestParam Integer id) {
        return orderRepository.findById(id);
    }
}