package com.example.phase_2.Accounts;

/*import Order_and_payment.Discount;
import Order_and_payment.Refund_Requests;*/

import java.util.Scanner;

import org.springframework.stereotype.Component;

public class Admin extends Account {
    private static int current_admin_id=1;

    public Admin (String mail,String pass)
    {
        email=mail;
        password=pass;
        id=current_admin_id;
        current_admin_id++;
    }

    public String get_mail() {
        return email;
    }

    public String get_password() {
        return password ;
    }

    public int get_id() {
        return id;
    }
}
