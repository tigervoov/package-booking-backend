package com.oocl.packagebooking.controller;


import com.oocl.packagebooking.entity.Order;
import com.oocl.packagebooking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/expressOrders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    public ResponseEntity getAllItem(){
        List<Order> allItem=orderService.getAllOrders();
        return ResponseEntity.ok().body(allItem);
    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity addNewOrders(@RequestBody Order newItem){
        String message=orderService.addExpressOrder(newItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }


}
