package com.example.phase_2.services.Internet;
import com.example.phase_2.order_and_payment.*;
import com.example.phase_2.services.*;

import java.util.Scanner;

public class Orange_Internet extends Internet_Service{

    public Orange_Internet() {}

    public  String start_service_logic(Order current_order,String number,double fees)
    {
        current_order.total_fees=fees;
        current_order.service_name="Orange Internet";
        current_order.service_type="internet";
        current_order.status="not completed";

        return "Thank you for using Orange Internet \n";
    }

}
