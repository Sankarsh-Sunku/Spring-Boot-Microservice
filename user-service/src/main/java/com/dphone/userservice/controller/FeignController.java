package com.dphone.userservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dphone.userservice.bean.BuyingDetails;
import com.dphone.userservice.bean.CustomerBean;
import com.dphone.userservice.bean.ReferralBean;
import com.dphone.userservice.feign.Feign1;
import com.dphone.userservice.feign.Feign2;
import com.dphone.userservice.feign.Feign3;

@RestController
public class FeignController {
	
	@Autowired
	private Feign1 feign1;
	@Autowired
	private Feign2 feign2;
	@Autowired
	private Feign3 feign3;
	
	@DeleteMapping("deleteCustByUserId/{userId}")
	public String deleteCustByUserId(@PathVariable int userId) {
		String msg = feign1.deleteCustByUserId(userId);
		return msg;
	}
	
	@PostMapping("userTocustomer/{userId}/{referralId}")
	public Map<String,Object> userToCustomer(@RequestBody CustomerBean customerBean,@PathVariable int userId,@PathVariable int referralId) {
		Map<String,Object> msg = feign1.addCustomer(customerBean, userId, referralId);
		return msg;
	}
	
	@DeleteMapping("deleteRefByUserId/{userId}")
	public String deleteRefByUserId(@PathVariable int userId) {
		String msg = feign2.deleteRefByUserId(userId);
		return msg;
	}
	
	@DeleteMapping("deleteDetailsByUserId/{userId}")
	public String deleteDetailsByUserId(@PathVariable int userId) {
		String msg = feign3.deleteDetailsByUserId(userId);
		return msg;
	}
	
	@PostMapping("detailsOfCustomer/{customerId}")
	public Map<String, Object> addDetails(@RequestBody BuyingDetails bean,@PathVariable int customerId){
		Map<String,Object> msg = feign3.addDetails(bean, customerId);
		return msg;
	}
	
	@GetMapping("getRefByUserId/{userId}")
	public ReferralBean getRefByUserId(@PathVariable int userId) {
		ReferralBean bean = feign2.showReferralByUserId(userId);
		return bean;
	}
	
	@PutMapping("updateRefByUserId/{userId}")
	public String updateRefByUserId(@PathVariable int userId,@RequestBody ReferralBean bean) {
		String msg = feign2.updateReferralByUserId(userId, bean);
		return msg;
	}
	
	@PutMapping("updateCustByUserId/{userId}")
	public String updateCustByUserId(@PathVariable int userId,@RequestBody CustomerBean bean) {
		String msg = feign1.updateCustByUserId(userId, bean);
		return msg;
	}
	
	@GetMapping("getCustByUserId/{userId}")
	public CustomerBean getCustByUserId(@PathVariable int userId) {
		CustomerBean bean = feign1.getCustByUserId(userId);
		return bean;
	}
}
