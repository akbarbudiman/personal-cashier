package com.akbarbudiman.cashier.models.response;

import com.akbarbudiman.cashier.models.entity.UserStoreRole;
import com.akbarbudiman.cashier.models.pojo.UserRole;

public class StoreUserSingleResponse {

	private Long userId;
	private String userName;
	private UserRole role;

	public StoreUserSingleResponse(UserStoreRole entity) {
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUserName();
		this.role = entity.getRole();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
