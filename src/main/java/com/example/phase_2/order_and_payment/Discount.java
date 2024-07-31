package com.example.phase_2.order_and_payment;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class Discount {
    private double first_time_discount_percent;
    private HashMap<String,Double> specific_discounts;
    private static Discount current_discount = new Discount();

    //applying Singleton pattern.
    private Discount(){
        first_time_discount_percent=0;
        specific_discounts=new HashMap<String, Double>();
    }

    public static Discount getInstance()
    {
        if (current_discount==null)
            current_discount = new Discount();
        return current_discount;
    }



    //user specific
    public String check_use_discount(Order current_order)
    {
        String msg="";
        boolean flag=false;
        //first
        if (current_order.id==1 && first_time_discount_percent!=0)
        {

            current_order.total_fees-=(current_order.total_fees*(first_time_discount_percent/100.0));
            flag=true;
            msg+=(first_time_discount_percent+"% discount has been added on your first order.\n");
        }

        if (specific_discounts.containsKey(current_order.service_type))
        {
            double specific_discount=specific_discounts.get(current_order.service_type);
            current_order.total_fees-=(current_order.total_fees*(specific_discount/100.0));
            flag=true;
            msg+=(specific_discount+"% discount has been added on your"+ current_order.service_name +" order.");
        }

        if (flag==true)
        {
            msg+=("Total cost after applying discount: "+current_order.total_fees+"\n");
            System.out.println();
        }

        return msg;


    }

    //admin specific
    public String add_specific_discount(String service_type,double discount_value)
    {
        String message=("you added a discount of "+discount_value+"% to "+service_type+" Service");
        service_type=service_type.toLowerCase();
        specific_discounts.put(service_type,discount_value);

        return message;
    }

    //admin specific
    public String add_first_time_discount(double discount_precent)
    {
        first_time_discount_percent=discount_precent;
        return("you added a discount of "+discount_precent+"% to first time orders.");
    }

}

