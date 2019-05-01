package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.OrderRepository;
import com.github.bosik927.model.repository.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Optional;

import static com.github.bosik927.model.repository.control.OrderController.ORDER_BASIC_PATH;

@Controller
@RequestMapping(path = ORDER_BASIC_PATH)
public class OrderController {

    public static final String ORDER_BASIC_PATH = "/order";

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path ="/add")
    public @ResponseBody
    String addNewOrder(@RequestParam Timestamp timestamp) {
        OrderEntity accountType = new OrderEntity();
        accountType.setData(timestamp);
        orderRepository.save(accountType);
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