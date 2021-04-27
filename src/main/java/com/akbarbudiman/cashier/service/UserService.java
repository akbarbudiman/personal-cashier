package com.akbarbudiman.cashier.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akbarbudiman.cashier.models.entity.Session;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.exception.NotFoundException;
import com.akbarbudiman.cashier.models.exception.NotFoundException.Entity;
import com.akbarbudiman.cashier.models.request.RegisterRequest;
import com.akbarbudiman.cashier.models.response.UserResponse;
import com.akbarbudiman.cashier.repo.UserRepo;
import com.akbarbudiman.cashier.util.PasswordHash;

@Service
public class UserService {

	@Autowired UserRepo repo;
	@Autowired PasswordHash passwordHash;
	
	public User findByUserName(String userName) {
		return repo.findByUserName(userName);
	}
	
	public User findByUserId(Long userIdToRemove) {
		Optional<User> user = repo.findById(userIdToRemove);
		if (user.isPresent()) return user.get();
		else throw new NotFoundException(Entity.USER, userIdToRemove);
	}
	
	public boolean isUserNameUnique(String desiredUserName) {
		User usedUserName = findByUserName(desiredUserName);
		return usedUserName == null;
	}
	
	public UserResponse createUser(RegisterRequest registerRequest) {
		User user = new User();
		user.setUserName(registerRequest.getUserName());
		user.setPassword(passwordHash.setPlainPassword(registerRequest.getPassword()).hash());
		repo.save(user);
		
		UserResponse response = new UserResponse(user);
		return response;
	}
	
	public void udpateUserSession(User user, Session session) {
		user.setSession(session);
		repo.save(user);
	}

}
