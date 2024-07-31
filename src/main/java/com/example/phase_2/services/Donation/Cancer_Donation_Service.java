package com.example.phase_2.services.Donation;

import com.example.phase_2.order_and_payment.*;


public class Cancer_Donation_Service extends Donation_Service{

    public  Cancer_Donation_Service()
    {

    }


    public String start_service_logic(Order current_order,String phone_number,double fees) {

        current_order.total_fees=fees;
        current_order.service_name="Cancer donation";
        current_order.service_type="donation";
        current_order.status="not completed";

        return "Thank you for donating to cure cancer \n";
    }

    @Override
    public String type() {
        return "Cancer";
    }
}
