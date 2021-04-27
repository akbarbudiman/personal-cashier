package com.akbarbudiman.cashier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akbarbudiman.cashier.models.entity.Store;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.entity.UserStoreRole;
import com.akbarbudiman.cashier.models.exception.NotFoundException;
import com.akbarbudiman.cashier.models.exception.NotFoundException.Entity;
import com.akbarbudiman.cashier.models.pojo.UserRole;
import com.akbarbudiman.cashier.repo.UserStoreRoleRepo;
import com.akbarbudiman.cashier.util.NullityChecker;

@Service
public class UserStoreRoleService {
	
	@Autowired UserStoreRoleRepo repo;

	public UserStoreRole addUserStoreRole(User user, Store store, UserRole role) {
		UserStoreRole userRole = new UserStoreRole();
		userRole.setUser(user);
		userRole.setStore(store);
		userRole.setRole(role);
		repo.save(userRole);
		return userRole;
	}
	
	public UserStoreRole findUserStoreRole(User user, Store store) {
		List<UserStoreRole> entities = repo.findByUserAndStore(user, store);
		if (NullityChecker.isListEmpty(entities)) 
			throw new NotFoundException(Entity.USER_STORE_ROLE);
		else
			return entities.get(0);
	}
	
	public List<UserStoreRole> findAllUserStoreRoleInStore(Store store) {
		return repo.findByStore(store);
	}

	public void remove(UserStoreRole userStoreRole) {
		repo.delete(userStoreRole);
	}
	
}
