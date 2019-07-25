package com.oocl.packagebooking.service;


import com.oocl.packagebooking.entity.Order;
import com.oocl.packagebooking.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public String addExpressOrder(Order newItem) {
        List<Order> listOrders=getAllOrders();
        long itemQuantity=listOrders.stream().filter(item->
            item.getOrder_number().equals(newItem.getOrder_number())
        ).count();
        if(itemQuantity>0){
            return "此快递单已添加，请勿重复添加";
        }else {
            orderRepository.saveAndFlush(newItem);
            return  "添加成功";
        }
    }
}
