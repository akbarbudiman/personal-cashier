package com.akbarbudiman.cashier.models.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginRequest {
	
	@NotNull
	@Size(min = 1)
	private String userName;
	
	@NotNull
	@Size(min = 6)
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
