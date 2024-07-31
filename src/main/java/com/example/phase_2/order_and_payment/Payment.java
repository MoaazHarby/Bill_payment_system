package com.example.phase_2.order_and_payment;


import org.springframework.beans.factory.annotation.Autowired;

public interface Payment {
    public String pay(Order user_order);

}
