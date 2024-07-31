package com.example.phase_2.order_and_payment;

public class Cash_Payment implements Payment{
    @Override
    public String pay(Order user_order) {

        user_order.status="Completed";
        return ("you have chosen paying to pay with cash, Thank you. \n");

    }
}
