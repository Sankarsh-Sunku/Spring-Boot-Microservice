package com.dphone.userservice.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dphone.userservice.bean.BuyingDetails;
import com.dphone.userservice.bean.CustomerBean;
import com.dphone.userservice.bean.ReferralBean;
import com.dphone.userservice.bean.UserBean;
import com.dphone.userservice.service.UserServiceImpl;

import jakarta.ws.rs.Path;


@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private FeignController feign;
	
	
	@PostMapping("/adduser")
	public ResponseEntity<?> addUser(@RequestBody UserBean bean){
		
		String message = userServiceImpl.addUser(bean);
		return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/userToCustomer/{userId}/{referralId}")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<?> userToCustomer(@RequestBody CustomerBean customerBean,@PathVariable int userId,@PathVariable int referralId){
		return new ResponseEntity<>(feign.userToCustomer(customerBean, userId, referralId),HttpStatus.OK);
	}
	
	@GetMapping("/selectalluser")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> selectAll() {

		List<UserBean> message = userServiceImpl.selectAll();

		return new ResponseEntity<>(message, HttpStatus.OK);

	}
	
	@GetMapping("finduserbyid/{userId}")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<?> findUserById(@PathVariable int userId) {

		try {
			UserBean message = userServiceImpl.findUserById(userId);
			return new ResponseEntity<>(message, HttpStatus.OK);

		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("updateuser/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody UserBean bean) {

		try {
			String message = userServiceImpl.updateUser(userId, bean);
			if(userId == bean.getUserId()) {
			ReferralBean referralBean = feign.getRefByUserId(userId);
			referralBean.setReferralFirstName(bean.getFirstName());
			referralBean.setReferralLastName(bean.getLastName());
			referralBean.setMobile(bean.getMobile());
			referralBean.setEmail(bean.getEmail());
			String msg = feign.updateRefByUserId(userId, referralBean);
			CustomerBean customerBean = feign.getCustByUserId(userId);
			customerBean.setFirstName(bean.getFirstName());
			customerBean.setLastName(bean.getLastName());
			customerBean.setEmail(bean.getEmail());
			customerBean.setMobile(bean.getMobile());
			String msg2 = feign.updateCustByUserId(userId, customerBean);
			}
			return new ResponseEntity<>(message, HttpStatus.OK);

		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>("Customer with the given ID is not Found", HttpStatus.NOT_FOUND);
		}

	}
	
	
	@DeleteMapping("deleteUser/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId) {
		try {
			String msg1 = feign.deleteDetailsByUserId(userId);
			String msg2 = feign.deleteCustByUserId(userId);
			String msg3 = feign.deleteRefByUserId(userId);
			String message = userServiceImpl.deleteUser(userId);
			return new ResponseEntity<>(message, HttpStatus.OK);

		} catch (NoSuchElementException e) {
			System.err.println(e);
			return new ResponseEntity<>("User with the given ID is not Found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>("No elements present", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("getUserByUserName/{userName}")
	public ResponseEntity<?> getUserByName(@PathVariable String userName){
		UserBean userBean = userServiceImpl.getUserByUserName(userName);
		return new ResponseEntity<>(userBean,HttpStatus.OK);
		
	}
	
	@PostMapping("/addDetails/{customerId}")
	public ResponseEntity<?> addDetails(@RequestBody BuyingDetails bean,@PathVariable int customerId){
		return new ResponseEntity<>(feign.addDetails(bean, customerId),HttpStatus.OK);
	}
	
	

}
