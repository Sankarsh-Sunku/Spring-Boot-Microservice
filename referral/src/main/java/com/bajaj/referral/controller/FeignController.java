package com.bajaj.referral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bajaj.referral.bean.UserBean;
import com.bajaj.referral.feign.ReferralUserFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("feign")
public class FeignController {
	@Autowired
	private ReferralUserFeign feign;


	@GetMapping("/getUserId/{userId}")
	@CircuitBreaker(name="referralService", fallbackMethod = "referralFallback")
	public UserBean getUserId(@PathVariable int userId) {
		UserBean bean = feign.getUserById(userId);
		return bean;
	}
	public ResponseEntity<String> referralFallback(Exception e){
        return new ResponseEntity<String>("Service is temporarily Down", HttpStatus.OK);

    }

}