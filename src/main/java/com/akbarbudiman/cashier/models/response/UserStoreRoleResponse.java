package com.akbarbudiman.cashier.models.response;

import com.akbarbudiman.cashier.models.entity.UserStoreRole;
import com.akbarbudiman.cashier.models.pojo.UserRole;

public class UserStoreRoleResponse {

	private UserResponse user;
	private StoreResponse store;
	private UserRole role;
	
	public UserStoreRoleResponse(UserStoreRole entity) {
		this.user = new UserResponse(entity.getUser());
		this.store = new StoreResponse(entity.getStore());
		this.role = entity.getRole();
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

	public StoreResponse getStore() {
		return store;
	}

	public void setStore(StoreResponse store) {
		this.store = store;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
