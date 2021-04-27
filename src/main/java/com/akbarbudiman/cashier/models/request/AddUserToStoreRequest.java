package com.akbarbudiman.cashier.models.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.akbarbudiman.cashier.models.pojo.UserRole;

public class AddUserToStoreRequest {

	@NotNull(message = "Username is mandatory")
	@Size(min = 1)
	private String userName;

	@NotNull(message = "User role is mandatory")
	private UserRole role;

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
