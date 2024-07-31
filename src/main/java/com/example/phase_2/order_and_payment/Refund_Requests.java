package com.example.phase_2.order_and_payment;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class Refund_Requests {
    private static Refund_Requests obj; 
    Vector<Order> refund_requests;

    // private constructor to force use of
    // getInstance() to create Singleton object
    private Refund_Requests() {
        refund_requests=new Vector<Order>();
    }

    public static Refund_Requests get_object()
    {
        if (obj==null)
            obj = new Refund_Requests();
        return obj;
    }

    public String print_requests() {
        String msg="";
        for (int i = 0; i < refund_requests.size(); i++) {
            msg+=("-User Id:"+refund_requests.elementAt(i).user.get_id()+"  "+refund_requests.elementAt(i).print_order_details());
        }
        if (msg.equals(""))
            return "no refund requests to show.";
        else
            return msg;
    }

    public String add_refund_request(Order requested_order)
    {
        requested_order.status="Refund Requested";
        refund_requests.add(new Order(requested_order));
        return ("your order refund request now is waiting for admin's approval");
    }
    
    public String select_refund_order_request(int order_id)
    {
        if (order_id>refund_requests.size())
            return "no matching order id";
        else
        {
            String msg="";

            order_id--;
            Order current_order=refund_requests.elementAt(order_id);
            current_order.user.wallet.refund_to_balance(current_order.total_fees);

            for (int i = 0; i <current_order.user.orders.size() ; i++) {
                if (current_order.user.orders.elementAt(i).id==current_order.id)
                {
                    current_order.user.orders.elementAt(i).status="Refunded";
                }
            }

            refund_requests.remove(current_order);
            msg+=("Order refund request is accepted for user: "+current_order.user.get_mail()+", "+current_order.total_fees+" has been refunded to the user's wallet.");

            //adding refunded order to logs.
            Logs log = Logs.getobj();
            log.add_refunded_order(current_order);
            log.remove_from_done_orders(current_order);

            String wallet_msg="-  "+current_order.total_fees+" has been refunded to the user: "+current_order.user.get_mail()+" wallet by admin due to refund.";
            log.add_wallet_transactions_by_admin(wallet_msg);

            return msg;
        }

    }
   

}
