package com.akbarbudiman.cashier.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akbarbudiman.cashier.models.entity.Store;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.entity.UserStoreRole;

public interface UserStoreRoleRepo extends JpaRepository<UserStoreRole, Long> {

	public List<UserStoreRole> findByUserAndStore(User user, Store store);
	
	public List<UserStoreRole> findByStore(Store store);
	
}
