package com.dphone.userservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dphone.userservice.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer> {
	
	@Query("SELECT u FROM UserEntity u WHERE u.userName =?1")
	public Optional<UserEntity> findByUserName(String userName);
	
}
