package com.example.phase_2.order_and_payment;

public class Wallet_Payment implements Payment {


    @Override
    public String pay(Order user_order) {
        String msg="";
        if (user_order.total_fees>user_order.user.get_wallet_Balance())
        {
            msg+=("Wallet balance is not sufficent, please try another payment method \n");
            return msg;
        }
        else
        {
            msg+=("You have successfully paid with your wallet balance. \n");
            user_order.user.wallet.deduce_from_balance(user_order.total_fees);
            msg+=("your current wallet balance is: "+user_order.user.get_wallet_Balance()+"\n");
            user_order.status="Completed";
            return msg;
        }



    }


}
