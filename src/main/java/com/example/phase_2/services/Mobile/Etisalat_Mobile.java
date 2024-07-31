package com.example.phase_2.services.Mobile;
import com.example.phase_2.order_and_payment.*;
import com.example.phase_2.services.*;

import java.util.Scanner;

public class Etisalat_Mobile extends Mobile_service{

    public Etisalat_Mobile()
    {

    }


    public String start_service_logic(Order current_order,String phone_number,double fees) {


        current_order.total_fees=fees;
        current_order.service_name="Etisalat Mobile";
        current_order.service_type="mobile";
        current_order.status="not completed";
        return "Thank you for using Etisalat Mobile \n";
    }
}
