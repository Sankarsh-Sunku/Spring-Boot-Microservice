package com.dphone.userservice.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dphone.userservice.bean.BuyingDetails;


@FeignClient(name = "BuyingDetailsService",url = "http://localhost:8084/productDetails/")
public interface Feign3 {
	@DeleteMapping("deleteDetailsByUserId/{userId}")
	public String deleteDetailsByUserId(@PathVariable int userId);
	@PostMapping("/addDetails/{customerId}")
	public Map<String,Object> addDetails(@RequestBody BuyingDetails bean,@PathVariable int customerId);
}
