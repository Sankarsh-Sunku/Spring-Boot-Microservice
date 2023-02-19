package com.bajaj.referral.service;


import java.util.List;

import com.bajaj.referral.bean.ReferralBean;
import com.bajaj.referral.bean.UserBean;
import com.bajaj.referral.entity.ReferralEntity;


public interface ReferralService { 
	
	public String addReferral(UserBean userBean,ReferralBean bean);
	
	public String deleteReferral(int referralId);
	
	public String updateReferral(int id,ReferralBean bean);
	
	public ReferralBean showReferral(int id);
	
	public List<ReferralBean> showReferral();
	
	public String deleteReferralById(int userId);
	
	public ReferralBean showReferralByUserId(int userId);
	
	public String updateReferralByUserId(int userId,ReferralBean bean);

}
