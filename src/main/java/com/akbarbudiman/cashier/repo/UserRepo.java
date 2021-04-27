package com.akbarbudiman.cashier.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akbarbudiman.cashier.models.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	public User findByUserName(String userName);
	
}
