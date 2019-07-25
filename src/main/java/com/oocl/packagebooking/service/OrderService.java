package com.oocl.packagebooking.service;


import com.oocl.packagebooking.entity.Order;
import com.oocl.packagebooking.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public String expressOrdersTime(String order_number,Order order) {
        List<Order> listOrders=getAllOrders();
        List<Order> orderItem=listOrders.stream().filter(item->item.getOrder_number().equals(order_number))
                .collect(Collectors.toList());
        if(orderItem.get(0).getOrder_status().equals("未取件")){
            orderItem.get(0).setOrder_status("已预约");
            orderItem.get(0).setAppointment_time(order.getAppointment_time());
            orderRepository.saveAndFlush(orderItem.get(0));
            return "预约成功";
        }else{
            return "此订单不能进行此操作";
        }
    }

    public void changeStatusToReceivedById(String orderid) {
        List<Order> listOrders=getAllOrders();
        List<Order> orderItem=listOrders.stream().filter(item->item.getId().equals(orderid))
                .collect(Collectors.toList());
        listOrders.get(0).setOrder_status("已取件");
        listOrders.get(0).setAppointment_time(0);
        orderRepository.saveAndFlush(listOrders.get(0));
    }
}
