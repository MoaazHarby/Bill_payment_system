package com.example.phase_2.order_and_payment;


import java.util.*;

public class Payment_factory {

    public Payment create_payment(String payment_method)
    {
        payment_method=payment_method.toLowerCase();
        Payment payment;
        if (payment_method.equals("cash"))
        {
            payment=new Cash_Payment();
        }
        else if (payment_method.equals("credit_card")|| payment_method.equals("credit card") || payment_method.equals("card")||payment_method.equals("credit"))
        {
            payment=new Credit_Card_payment();
        }
        else if (payment_method.equals("wallet"))
        {
            payment=new Wallet_Payment();
        }
        else
        {
            return null;
        }

        return payment;

    }


}
