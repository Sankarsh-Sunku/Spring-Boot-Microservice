package com.dphone.userservice.service;

import java.util.List;

import com.dphone.userservice.bean.UserBean;

public interface UserService {

	
	public String addUser(UserBean userBean);
	public String deleteUser(int userId);
	public String updateUser(int userId,UserBean bean);
	public  UserBean findUserById(int userId);
	public List<UserBean> selectAll();
	public UserBean getUserByUserName(String userName);
}
