package com.example.phase_2.user;

import com.example.phase_2.order_and_payment.*;
import com.example.phase_2.services.Donation.Donation_Service;
import com.example.phase_2.services.Internet.Internet_Service;
import com.example.phase_2.services.Landline.Landline_Service;
import com.example.phase_2.services.Mobile.Mobile_service;
import com.example.phase_2.services.Services_Factory.Donation_service_Factory;
import com.example.phase_2.services.Services_Factory.Internet_service_Factory;
import com.example.phase_2.services.Services_Factory.Landline_service_Factory;
import com.example.phase_2.services.Services_Factory.Mobile_service_Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.phase_2.Accounts.*;

@RestController
public class User_Controller {
	
	private User current_User;
	@Autowired
	private User_Factory user_factory;
	@Autowired
	private Discount discount_table;

	@Autowired
	private Refund_Requests refund_request_operator;

	public User_Controller() {
		
	}
	
	@PostMapping("/user_sign_in")
	public String sign_up(@RequestParam String email , @RequestParam String password) {

		return user_factory.login(email, password);
	}
	
	@PostMapping("/user_sign_up")
	public String sign_in(@RequestParam String email , @RequestParam String password) {

		
		return user_factory.signUp(email, password);
	}

	@PostMapping("/internet_service")
	public String internet_service(@RequestParam int user_id,@RequestParam String provider ,@RequestParam String number, @RequestParam String payment_method,@RequestParam double fees)
	{
		current_User=user_factory.get_user_by_id(user_id);
		if (current_User==null)
			return "no matching user id, please send valid id";

		String output_message="";
		Internet_service_Factory internet_factory =new Internet_service_Factory();
		Internet_Service internet_service=internet_factory.select_service_provider(provider);

		if (internet_service==null)
			return "no matching service provider";

		output_message+=internet_service.start_service_logic(current_User.current_order,number,fees);
		output_message+=discount_table.check_use_discount(current_User.current_order);

		Payment_factory payment_factory=new Payment_factory();
		Payment payment_gateway=payment_factory.create_payment(payment_method);
		if (payment_gateway==null)
		{
			return "no matching payment method";
		}

		String pay_message=payment_gateway.pay(current_User.current_order);

		if (pay_message.equals("Wallet balance is not sufficent, please try another payment method \n"))
			return "Wallet balance is not sufficent, please try another payment method \n";

		output_message+=pay_message;
		current_User.add_order_to_history();

		return output_message;

	}


	@PostMapping("/mobile_service")
	public String mobile_service(@RequestParam int user_id,@RequestParam String provider,@RequestParam String number, @RequestParam String payment_method,@RequestParam double fees)
	{
		current_User=user_factory.get_user_by_id(user_id);
		if (current_User==null)
			return "no matching user id, please send valid id";

		String output_message="";
		Mobile_service_Factory mobile_factory=new Mobile_service_Factory();
		Mobile_service mobile_service=mobile_factory.select_service_provider(provider);

		if (mobile_service==null)
			return "no matching service provider";

		output_message+=mobile_service.start_service_logic(current_User.current_order,number,fees);
		output_message+=discount_table.check_use_discount(current_User.current_order);

		Payment_factory payment_factory=new Payment_factory();
		Payment payment_gateway=payment_factory.create_payment(payment_method);
		if (payment_gateway==null)
		{
			return "no matching payment method";
		}

		String pay_message=payment_gateway.pay(current_User.current_order);

		if (pay_message.equals("Wallet balance is not sufficent, please try another payment method \n"))
			return "Wallet balance is not sufficent, please try another payment method \n";

		output_message+=pay_message;
		current_User.add_order_to_history();

		return output_message;

	}


	@PostMapping("/landline_service")
	public String landline_service(@RequestParam int user_id,@RequestParam String payment_type ,@RequestParam String number, @RequestParam String payment_method,@RequestParam double fees)
	{
		current_User=user_factory.get_user_by_id(user_id);
		if (current_User==null)
			return "no matching user id, please send valid id";

		String output_message="";
		Landline_service_Factory landline_factory=new Landline_service_Factory();
		Landline_Service landline_service=landline_factory.select_landline_service(payment_type);

		if (landline_service==null)
			return "no matching landline type";

		output_message+=landline_service.start_service_logic(current_User.current_order,number,fees);
		output_message+=discount_table.check_use_discount(current_User.current_order);

		Payment_factory payment_factory=new Payment_factory();
		Payment payment_gateway=payment_factory.create_payment(payment_method);
		if (payment_gateway==null)
		{
			return "no matching payment method";
		}

		String pay_message=payment_gateway.pay(current_User.current_order);

		if (pay_message.equals("Wallet balance is not sufficent, please try another payment method \n"))
			return "Wallet balance is not sufficent, please try another payment method \n";

		output_message+=pay_message;
		current_User.add_order_to_history();

		return output_message;
	}


	@PostMapping("/donation_service")
	public String donation_service(@RequestParam int user_id,@RequestParam String provider , @RequestParam String payment_method,@RequestParam double fees)
	{

		current_User=user_factory.get_user_by_id(user_id);
		if (current_User==null)
			return "no matching user id, please send valid id";

		String output_message="";
		Donation_service_Factory service_factory=new Donation_service_Factory();
		Donation_Service donation_service=null;
		donation_service=service_factory.select_donation_service(provider);

		if (donation_service==null)
			return "no matching donation service";

		output_message+=donation_service.start_service_logic(current_User.current_order,"0",fees);
		output_message+=discount_table.check_use_discount(current_User.current_order);

		Payment_factory payment_factory=new Payment_factory();
		Payment payment_gateway=payment_factory.create_payment(payment_method);
		if (payment_gateway==null)
			return "no matching payment method";

		String pay_message=payment_gateway.pay(current_User.current_order);

		if (pay_message.equals("Wallet balance is not sufficent, please try another payment method \n"))
			return "Wallet balance is not sufficent, please try another payment method \n";

		output_message+=pay_message;

		current_User.add_order_to_history();

		return output_message;
	}

	@GetMapping("/wallet_balance")
	public String show_wallet_balance(@RequestParam int user_id)
	{
		current_User=user_factory.get_user_by_id(user_id);
		if (current_User==null)
			return "no matching user id, please send valid id";

		return current_User.wallet.showBalance();

	}

	@PostMapping("/add_to_wallet")
	public String add_to_wallet(@RequestParam int user_id,@RequestParam double fees)
	{
		current_User=user_factory.get_user_by_id(user_id);
		if (current_User==null)
			return "no matching user id, please send valid id";

		return current_User.wallet.add_to_balance(current_User,fees);

	}

	@GetMapping("/show_order_history")
	public String show_order_history(@RequestParam int user_id)
	{
		current_User=user_factory.get_user_by_id(user_id);
		if (current_User==null)
			return "no matching user id, please send valid id";

		return current_User.show_orders_history();

	}

	@PostMapping("/refund_order")
	public String request_refund_order(@RequestParam int user_id,@RequestParam int order_id)
	{
		current_User=user_factory.get_user_by_id(user_id);
		if (current_User==null)
			return "no matching user id, please send valid id";

		Order current_order=current_User.get_order_by_id(order_id);

		if (current_order==null)
			return "no matching order id.";

		if (current_order.status=="Refunded")
			return "order with this id was already refunded.";

		return refund_request_operator.add_refund_request(current_order);

	}

}
