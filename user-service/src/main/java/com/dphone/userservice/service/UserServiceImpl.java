package com.dphone.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.dphone.userservice.bean.UserBean;
import com.dphone.userservice.dao.UserDao;
import com.dphone.userservice.entity.UserEntity;

@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	private UserDao dao;
	@Autowired
	private PasswordEncoder passwordencoder;

	
	public String addUser(UserBean userBean) {
		// TODO Auto-generated method stub
		userBean.setPassword(passwordencoder.encode(userBean.getPassword()));
		if(userBean.getRoles()== null) {
			userBean.setRoles("ROLE_USER");
		}
		
		UserEntity entity =new UserEntity();
		BeanUtils.copyProperties(userBean, entity);
		dao.save(entity);
		
		return "Added";
	}


	@Override
	public String deleteUser(int userId) {
		// TODO Auto-generated method stub
		Optional<UserEntity> userEntity = dao.findById(userId);

		UserEntity userEntity2 = userEntity.get();
		UserBean bean=new UserBean();

		BeanUtils.copyProperties(userEntity2, bean);
		
		dao.delete(userEntity2);
		
		return "deleted:"+userEntity2;
	}


	@Override
	public String updateUser(int userId, UserBean bean) {
		// TODO Auto-generated method stub
		Optional<UserEntity> userEntity = dao.findById(userId);
		UserEntity user = userEntity.get();
		
		
		user.setUserId(bean.getUserId());
		user.setLastName(bean.getLastName());
		user.setAddress(bean.getAddress());
		user.setEmail(bean.getEmail());
		user.setUserName(bean.getUserName());
		user.setPassword(bean.getPassword());
		
		dao.save(user);
		return "updatedById :" + user;
	}


	@Override
	public UserBean findUserById(int userId) {
		// TODO Auto-generated method stub
		Optional<UserEntity> userEntity = dao.findById(userId);

		UserEntity userEntity2 = userEntity.get();

		UserBean bean=new UserBean();
		BeanUtils.copyProperties(userEntity2, bean);

		return bean;
	}


	@Override
	public List<UserBean> selectAll() {
		// TODO Auto-generated method stub
		
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		Iterable<UserEntity> it = dao.findAll();

		it.forEach(x -> {

			UserBean bean = new UserBean();
			BeanUtils.copyProperties(x, bean);
			userList.add(bean);

		});

		return userList;
	}
	
	@Override
	public UserBean getUserByUserName(String userName) {
		Optional<UserEntity> entity = dao.findByUserName(userName);
		UserBean bean =  new UserBean();
		if(entity.isPresent()) {
			BeanUtils.copyProperties(entity.get(), bean);
			return bean;
		}else {
			return null;
		}
	}

	
}
