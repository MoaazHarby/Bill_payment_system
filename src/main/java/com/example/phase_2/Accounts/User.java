package com.example.phase_2.Accounts;

import com.example.phase_2.order_and_payment.*;

import java.util.*;

//@Component
public class User extends Account {
    private static int current_user_id=1;
    public Vector<Order> orders;
    public Order current_order;
    public Wallet wallet;
	

    public  User (String mail,String pass)
    {
        email=mail;
        password=pass;
        id=current_user_id;
        wallet=new Wallet();
        current_user_id++;
        orders=new Vector<Order>();
        current_order=new Order(this);

    }


    public String get_mail() {
        return email;
    }

    public String get_password()
    {
        return password ;
    }


    public void check_discount(Order order )
    {
        Discount discount=Discount.getInstance();
        discount.check_use_discount(order);
    }
    public void add_order_to_history()
    {
        if (current_order.status.equals("not completed"))
        {
            current_order=new Order(this);
        }
        else
        {
            Logs log=Logs.getobj();
            log.add_done_orders(current_order);

            orders.addElement(current_order);
            current_order=new Order(this);
        }
    }
    public String show_orders_history()
    {
        String msg="";
        if (orders.size()==0)
            return "No orders were done to display.";
        else
        {
            for (int i = 0; i <orders.size(); i++) {
                msg+=("- "+orders.elementAt(i).print_order_details()+" \n");
            }
            return msg;
        }
    }
    public Order get_order_by_id(int order_id)
    {
        for (int i = 0; i <orders.size(); i++) {
            if (orders.elementAt(i).id==order_id)
            {
                return orders.elementAt(i);
            }
        }
        return null;
    }

    public double get_wallet_Balance()
    {
        return wallet.getBalance();
    }

}

