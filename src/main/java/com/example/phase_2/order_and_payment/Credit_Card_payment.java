package com.example.phase_2.order_and_payment;

import com.example.phase_2.Accounts.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class Credit_Card_payment implements Payment{

    @Override
    public String pay(Order user_order) {
        //we don't require card number and ccv
        //as we can't verify if they are true or not and we can't process these information.
        user_order.status="Completed";
        return ("you have chosen paying to pay with Credit Card, Thank you.\n");

    }

    public String add_to_wallet(User user, double cost) {

        //we don't require card number and ccv
        //as we can't verify if they are true or not and we can't process these information.

        Logs log=Logs.getobj();
        log.add_wallet_transactions(user,cost);

        return ("you have successfully added "+cost+" to your wallet balance using credit card payment.\n");


    }
}
