package com.dphone.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dphone.userservice.bean.ReferralBean;

@FeignClient(name = "Referral-Service",url = "http://localhost:8086/referral/")
public interface Feign2 {
	@DeleteMapping("deleteRefByUserId/{userId}")
	public String deleteRefByUserId(@PathVariable int userId);
	@GetMapping("showRefByUserId/{userId}")
	public ReferralBean showReferralByUserId(@PathVariable int userId);
	@PutMapping("updateRefByUserId/{userId}")
	public String updateReferralByUserId(@PathVariable int userId,@RequestBody ReferralBean bean);

}
