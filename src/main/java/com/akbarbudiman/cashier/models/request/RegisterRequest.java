package com.akbarbudiman.cashier.models.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterRequest {
	
	@NotNull(message = "Username is mandatory")
	@Size(min = 1)
	private String userName;

	@NotNull
	@Size(min = 6)
	private String password;

	@NotNull
	@Size(min = 6)
	private String confirmedPassword;

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

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}

}
