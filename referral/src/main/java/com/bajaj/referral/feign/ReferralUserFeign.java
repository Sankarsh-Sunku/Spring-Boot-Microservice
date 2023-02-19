package com.bajaj.referral.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bajaj.referral.bean.UserBean;

@FeignClient(name = "Userefeign",url = "http://localhost:8080/user")
public interface ReferralUserFeign {
	@GetMapping("/finduserbyid/{userId}")
	public UserBean getUserById(@PathVariable int userId);

}
