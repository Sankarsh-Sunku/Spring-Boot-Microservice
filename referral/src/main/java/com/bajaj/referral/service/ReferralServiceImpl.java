package com.bajaj.referral.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bajaj.referral.bean.ReferralBean;
import com.bajaj.referral.bean.UserBean;
import com.bajaj.referral.dao.ReferralDao;
import com.bajaj.referral.entity.ReferralEntity;

import jakarta.transaction.Transactional;

import java.util.Optional;
@Service
public class ReferralServiceImpl implements ReferralService {

	@Autowired
	private ReferralDao dao;
	
	@Override
	public String addReferral(UserBean userBean,ReferralBean bean) {
		if(userBean!=null) {
			bean.setUserId(userBean.getUserId());
			bean.setReferralFirstName(userBean.getFirstName());
			bean.setReferralLastName(userBean.getLastName());
			bean.setEmail(userBean.getEmail());
			bean.setReferralPoints(0);
			bean.setStatus("notReferred");
			bean.setDateOfReferral(java.time.LocalDate.now());
			bean.setMobile(userBean.getMobile());
	 		ReferralEntity entity = new ReferralEntity();
	 		BeanUtils.copyProperties(bean, entity);
	 		dao.save(entity);
			return "Successfully new referral added";
	 		}
	 		else {
	 			return "Check Once Again";
	 		}
		
	}

	@Override
	public String deleteReferral(int id) {
				
		if(dao.findById(id).isPresent()) {
			dao.deleteById(id);
			return "Referral deleted";
		}else {
			return "No such referral found to delete";
		}
		
	}

	@Override
	public String updateReferral(int id,ReferralBean bean) {
		
		ReferralEntity entity = dao.findById(id).get();
		ReferralEntity entity2 = dao.findById(bean.getReferralId()).get();

			
		if(entity == entity2 && entity!=null && entity2!=null) {
			
			entity.setDateOfReferral(bean.getDateOfReferral());
			entity.setEmail(bean.getEmail());
			entity.setMobile(bean.getMobile());
			entity.setReferralFirstName(bean.getReferralFirstName());
			entity.setReferralLastName(bean.getReferralLastName());
			entity.setStatus(bean.getStatus());
			entity.setReferralPoints(bean.getReferralPoints());
			entity.setUserId(bean.getUserId());
			dao.save(entity);
			return "Referral Updated";
			
		}else {
			return "No such referral found to update";
		}
	
		
		
	}

	@Override
	public ReferralBean showReferral(int id) {
		
		if(dao.findById(id).isPresent()) {
			ReferralEntity referral = dao.findById(id).get();
			ReferralBean bean = new ReferralBean();
			BeanUtils.copyProperties(referral, bean);
			return bean;
		
		}else {
			return null;
		}
		
	}
	
	@Override
	public List<ReferralBean> showReferral() {
		List<ReferralEntity> listEntities = dao.findAll();
		List<ReferralBean> list = new LinkedList<>();
		listEntities.forEach(x->{
			ReferralBean bean = new ReferralBean();
			BeanUtils.copyProperties(x, bean);
			list.add(bean);
		});
		return list;
		
	}
	
	@Override
	@Transactional
	public String deleteReferralById(int userId) {
		Optional<ReferralEntity> entity = dao.findByUserId(userId);
		if(entity.isPresent()) {
			dao.deleteByUserId(userId);
			return "Successfully Deleted";
		}
		else {
			return "check userId Once Again";
		}
	}
	
	@Override
	public ReferralBean showReferralByUserId(int userId) {
		Optional<ReferralEntity> entity = dao.findByUserId(userId);
		ReferralBean bean = new ReferralBean();
		if(entity.isPresent()) {
			BeanUtils.copyProperties(entity.get(), bean);
			return bean;
		}else {
			return null;
		}
 	}
	
	@Override
	public String updateReferralByUserId(int userId,ReferralBean bean) {
		ReferralEntity entity = dao.findByUserId(userId).get();
		ReferralEntity entity2 = dao.findByUserId(bean.getUserId()).get();
		
		if(entity == entity2 && entity!=null && entity2!=null) {
			
			entity.setDateOfReferral(bean.getDateOfReferral());
			entity.setEmail(bean.getEmail());
			entity.setMobile(bean.getMobile());
			entity.setReferralFirstName(bean.getReferralFirstName());
			entity.setReferralLastName(bean.getReferralLastName());
			entity.setStatus(bean.getStatus());
			entity.setReferralPoints(bean.getReferralPoints());
			entity.setUserId(bean.getUserId());
			dao.save(entity);
			return "Referral Updated";
			
		}else {
			return "No such referral found to update";
		}
		
	}

}
