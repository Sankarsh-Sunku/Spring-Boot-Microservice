package com.bajaj.referral.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bajaj.referral.bean.ReferralBean;
import com.bajaj.referral.bean.UserBean;
import com.bajaj.referral.feign.ReferralUserFeign;
import com.bajaj.referral.service.ReferralService;

@RestController
@RequestMapping("referral")
public class ReferralController {
	
	@Autowired
	ReferralService service;
	@Autowired
	private FeignController feign;
	
	
	@GetMapping("/showref/{id}")
	public ResponseEntity<?> showReferral(@PathVariable int id){
		ReferralBean bean =  service.showReferral(id);
		return new ResponseEntity<>(bean,HttpStatus.OK);
	}
	
	@GetMapping("/showref")
	public ResponseEntity<?> showReferral(){
		List<ReferralBean> list = service.showReferral();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@PostMapping("/addref/{userId}")
	public ResponseEntity<String> addReferral(@PathVariable int userId,@RequestBody ReferralBean bean){
		UserBean userBean = new UserBean();
		userBean = feign.getUserId(userId);
		String message = service.addReferral(userBean,bean);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}

	@DeleteMapping("/deleteref/{referralId}")
	public ResponseEntity<?> deleteReferral(@PathVariable int referralId){
		String msg = service.deleteReferral(referralId);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/updateref/{referralId}")
	public ResponseEntity<?> updateRefrral(@PathVariable int referralId,@RequestBody ReferralBean bean){
		return new ResponseEntity<>(service.updateReferral(referralId,bean)+"",HttpStatus.OK);
	}
	
	@DeleteMapping("deleteRefByUserId/{userId}")
	public ResponseEntity<?> deleteReferralByUserId(@PathVariable int userId){
		return new ResponseEntity<>(service.deleteReferralById(userId),HttpStatus.OK);
	}
	
	@GetMapping("showRefByUserId/{userId}")
	public ResponseEntity<?> showReferralByUserId(@PathVariable int userId){
		return new ResponseEntity<>(service.showReferralByUserId(userId),HttpStatus.OK);
	}
	
	@PutMapping("updateRefByUserId/{userId}")
	public ResponseEntity<?> updateRefByUserId(@PathVariable int userId,@RequestBody ReferralBean bean){
		return new ResponseEntity<>(service.updateReferralByUserId(userId, bean),HttpStatus.OK);
	}
	
	
}
