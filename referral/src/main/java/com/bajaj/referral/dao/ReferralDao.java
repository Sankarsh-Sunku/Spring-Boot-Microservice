package com.bajaj.referral.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bajaj.referral.entity.ReferralEntity;

import java.util.Optional;

public interface ReferralDao extends JpaRepository<ReferralEntity, Integer>{
	@Query("SELECT r FROM ReferralEntity r WHERE r.userId = ?1")
	public Optional<ReferralEntity> findByUserId(int userId);
	@Modifying
	@Query("DELETE FROM ReferralEntity r WHERE r.userId = ?1")
	public void deleteByUserId(int userId);
	
	
}