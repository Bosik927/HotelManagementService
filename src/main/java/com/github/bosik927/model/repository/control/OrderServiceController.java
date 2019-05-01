package com.github.bosik927.model.repository.control;

import com.github.bosik927.model.repository.boundary.OrderServiceRepository;
import com.github.bosik927.model.repository.entity.OrderServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static com.github.bosik927.model.repository.control.OrderServiceController.ORDER_SERVICE_BASIC_PATH;

@Controller
@RequestMapping(path = ORDER_SERVICE_BASIC_PATH)
public class OrderServiceController {

    public static final String ORDER_SERVICE_BASIC_PATH = "/orderService";

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @GetMapping(path ="/add")
    public @ResponseBody
    String addNewOrder() {
        OrderServiceEntity orderServiceEntity = new OrderServiceEntity();
        //orderServiceEntity.setOrdersByOrderId();
        //orderServiceEntity.setOrderServiceId();
        orderServiceRepository.save(orderServiceEntity);
        return "SAVED";
    }

    @GetMapping(path = "/getAll")
    public @ResponseBody
    Iterable<OrderServiceEntity> getAllUsers() {
        return orderServiceRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody
    Optional<OrderServiceEntity> getById(@RequestParam Integer id) {
        return orderServiceRepository.findById(id);
    }
}