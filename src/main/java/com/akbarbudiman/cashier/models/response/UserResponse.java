package com.akbarbudiman.cashier.models.response;

import com.akbarbudiman.cashier.models.entity.User;

public class UserResponse {

	private Long id;
	private String userName;
	
	public UserResponse() {
		super();
	}
	
	public UserResponse(User entity) {
		this.id = entity.getId();
		this.userName = entity.getUserName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
