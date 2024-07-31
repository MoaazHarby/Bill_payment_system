package com.example.phase_2.admin;

import com.example.phase_2.order_and_payment.Discount;
import com.example.phase_2.order_and_payment.Logs;
import com.example.phase_2.order_and_payment.Refund_Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.phase_2.Accounts.Admin;
import com.example.phase_2.Accounts.Admin_Factory;

@RestController
public class Admin_Controller {

	public Admin curr_admin;
	@Autowired
	public Admin_Factory admin_Factory;
	@Autowired
	private Discount discount_table;

	@Autowired
	private Refund_Requests refund_requests;
	
	public Admin_Controller() {
		
	}
	
	@PostMapping("/admin_sign_in")
	public String sign_in(@RequestParam String email , @RequestParam String password) {
		
		return admin_Factory.login(email,password);
		
	}
	
	@PostMapping("/admin_sign_up")
	public String sign_up(@RequestParam String email , @RequestParam String password) {
		
		return admin_Factory.signUp(email, password);
	}

	@PostMapping("/add_first_time_discount")
	public String add_first_time_discount(@RequestParam int admin_id , @RequestParam double discount) {

		curr_admin=admin_Factory.get_admin_by_id(admin_id);
		if (curr_admin==null)
			return "no matching admin id, please send valid id";


		return discount_table.add_first_time_discount(discount);
	}

	@PostMapping("/add_service_discount")
	public String add_service_discount(@RequestParam int admin_id ,@RequestParam String service, @RequestParam double discount) {

		curr_admin=admin_Factory.get_admin_by_id(admin_id);
		if (curr_admin==null)
			return "no matching admin id, please send valid id";

		return discount_table.add_specific_discount(service,discount);
	}

	@GetMapping(value = "/show_refund_requests")
	public String show_refund_requests(@RequestParam int admin_id) {

		curr_admin=admin_Factory.get_admin_by_id(admin_id);
		if (curr_admin==null)
			return "no matching admin id, please send valid id";

		return refund_requests.print_requests();
	}

	@PostMapping("/accept_order_refund")
	public String accept_order_refund(@RequestParam int admin_id, @RequestParam int order_placement) {

		curr_admin=admin_Factory.get_admin_by_id(admin_id);
		if (curr_admin==null)
			return "no matching admin id, please send valid id";

		return refund_requests.select_refund_order_request(order_placement);
	}

	@GetMapping("/show_all_logs")
	public String show_logs(@RequestParam int admin_id) {

		curr_admin=admin_Factory.get_admin_by_id(admin_id);
		if (curr_admin==null)
			return "no matching admin id, please send valid id";

		Logs log=Logs.getobj();

		return log.show_all_logs();
	}


	
	
}
