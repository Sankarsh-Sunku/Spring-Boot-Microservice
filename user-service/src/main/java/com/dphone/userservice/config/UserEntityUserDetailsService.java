package com.dphone.userservice.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dphone.userservice.dao.UserDao;
import com.dphone.userservice.entity.UserEntity;

@Component
public class UserEntityUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserEntity> userInfo = dao.findByUserName(username);
        return userInfo.map(UserEntityUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
	}

}
