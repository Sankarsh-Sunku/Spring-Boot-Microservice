package com.dphone.userservice.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dphone.userservice.bean.CustomerBean;


@FeignClient(name = "CustomerFeign",url = "http://localhost:8082/customer")
public interface Feign1 {
	@DeleteMapping("deleteCustByUser/{userId}")
	public String deleteCustByUserId(@PathVariable int userId);
	@PostMapping("/addCustomer/{userId}/{referralId}")
	public Map<String,Object> addCustomer(@RequestBody CustomerBean customerBean,@PathVariable int userId,@PathVariable int referralId);
	@GetMapping("getCustByUserId/{userId}")
	public CustomerBean getCustByUserId(@PathVariable int userId);
	@PutMapping("updateCustByUserId/{userId}")
	public String updateCustByUserId(@PathVariable int userId,@RequestBody CustomerBean bean);
}
