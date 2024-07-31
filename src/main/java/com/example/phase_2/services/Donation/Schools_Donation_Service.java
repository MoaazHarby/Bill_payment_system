
package com.example.phase_2.services.Donation;

import com.example.phase_2.order_and_payment.*;

public class Schools_Donation_Service extends Donation_Service{
    public  Schools_Donation_Service()
    {

    }

    public String start_service_logic(Order current_order,String phone_number,double fees) {

        current_order.total_fees=fees;
        current_order.service_name="Schools donation";
        current_order.service_type="donation";
        current_order.status="not completed";

        return "Thank you for donating to schools \n";
    }

}
