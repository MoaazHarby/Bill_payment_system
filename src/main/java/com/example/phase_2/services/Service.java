package com.example.phase_2.services;
import com.example.phase_2.order_and_payment.*;

public abstract class Service {

    public abstract String start_service_logic(Order current_order,String number,double fees);

}
