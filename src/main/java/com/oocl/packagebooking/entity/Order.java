package com.oocl.packagebooking.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "express_orders")
public class Order {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String order_number;

    private String receiver;

    private String receiver_phone;

    private String order_status;

    private long appointment_time;

    public Order() {
    }

    public Order(String order_number, String receiver, String receiver_phone, String order_status) {
        this.order_number = order_number;
        this.receiver = receiver;
        this.receiver_phone = receiver_phone;
        this.order_status = order_status;
    }

    public Order(String order_number, String receiver, String receiver_phone, String order_status, long appointment_time) {
        this.order_number = order_number;
        this.receiver = receiver;
        this.receiver_phone = receiver_phone;
        this.order_status = order_status;
        this.appointment_time = appointment_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public long getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(long appointment_time) {
        this.appointment_time = appointment_time;
    }
}
