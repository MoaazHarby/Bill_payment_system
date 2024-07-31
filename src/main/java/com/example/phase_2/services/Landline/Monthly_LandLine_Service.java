package com.example.phase_2.services.Landline;

import com.example.phase_2.order_and_payment.*;

import java.util.Scanner;

public class Monthly_LandLine_Service extends Landline_Service{

    public Monthly_LandLine_Service()
    {
    }


    public String start_service_logic(Order current_order,String phone_number,double fees) {

        current_order.total_fees=fees;
        current_order.service_name="Monthly LandLine Service";
        current_order.service_type="landline";
        current_order.status="not completed";

        return "Thanks for choosing to pay your landline bills \n";

    }
}
