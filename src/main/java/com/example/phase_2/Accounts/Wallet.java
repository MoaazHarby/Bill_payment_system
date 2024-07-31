package com.example.phase_2.Accounts;
import com.example.phase_2.order_and_payment.*;
import java.util.Scanner;

public class Wallet {
    private double balance;
    public Wallet()
    {
        balance=0;
    }

    public String showBalance()
    {
       return ("Your wallet balance currently is: "+balance);
    }

    public double getBalance()
    {
        return balance;
    }

    public void deduce_from_balance(double cost)
    {
        balance-=cost;
    }

    public String add_to_balance(User user,double cost)
    {
        Credit_Card_payment payment=new Credit_Card_payment();
        balance+=cost;

        return payment.add_to_wallet(user,cost);

    }

    public void refund_to_balance(double refunded)
    {
        balance+=refunded;

    }
}
