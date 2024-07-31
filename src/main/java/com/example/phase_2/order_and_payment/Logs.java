package com.example.phase_2.order_and_payment;

import com.example.phase_2.Accounts.Admin;
import com.example.phase_2.Accounts.Admin_Factory;
import com.example.phase_2.Accounts.User;
import org.springframework.stereotype.Component;

import java.util.Vector;

public class Logs {

    protected Vector<Order> done_orders;
    protected Vector<Order> refunded_orders;
    protected Vector<String> wallet_transactions;

    private static Logs obj=null;

    // private constructor to force use of
    // getInstance() to create Singleton object
    public Logs() {

        done_orders=new Vector<Order>(0);
        refunded_orders=new Vector<Order>(0);
        wallet_transactions=new Vector<String>(0);
    }

    public static Logs getobj()
    {
        if (obj==null)
            obj = new Logs();

        return obj;
    }




    public void add_done_orders(Order curr_order)
    {
        done_orders.addElement(curr_order);
    }

    public void remove_from_done_orders(Order curr_order)
    {
        for (int i = 0; i < done_orders.size(); i++)
        {
            if (done_orders.elementAt(i).id==curr_order.id && done_orders.elementAt(i).user.id==curr_order.user.id)
            {
                done_orders.remove(i);
            }
        }
    }

    public void add_refunded_order(Order curr_order)
    {
        refunded_orders.addElement(curr_order);
    }

    public void add_wallet_transactions(User user,double cost)
    {
        String wallet_transaction_msg="- User: "+user.get_mail()+" has added "+ cost+" to his wallet balance";
        wallet_transactions.addElement(wallet_transaction_msg);
    }

    public void add_wallet_transactions_by_admin(String msg)
    {
        wallet_transactions.addElement(msg);
    }

    public String show_all_logs()
    {
        String msg="";

        msg+="Payment transactions: \n";
        msg+="--------------------------\n";

        if (done_orders.size()==0)
            msg+="no orders were done by any users.\n";
        else
        {
            for (int i = 0; i < done_orders.size(); i++) {
                msg+="- User : "+ done_orders.elementAt(i).user.get_mail() + " completed, "+done_orders.elementAt(i).print_order_details()+" \n ";
            }
        }

        msg+="\nrefunded transactions: \n";
        msg+="--------------------------\n";

        if (refunded_orders.size()==0)
            msg+="no orders were refunded to any users. \n";
        else
        {
            for (int i = 0; i < refunded_orders.size(); i++) {
                msg+="- User : "+ refunded_orders.elementAt(i).user.get_mail() + " order of id: "+refunded_orders.elementAt(i).id+" has been refunded \n ";
                msg+="  and balance: "+refunded_orders.elementAt(i).total_fees+" were added back to his wallet \n";
            }
        }

        msg+="\nadd to wallet transactions: \n";
        msg+="--------------------------\n";

        if (wallet_transactions.size()==0)
            msg+="no users added balance to their wallet \n";
        else
        {
            for (int i = 0; i < wallet_transactions.size(); i++) {
                msg+=wallet_transactions.elementAt(i) +"\n";
            }
        }

        return msg;


    }
}
