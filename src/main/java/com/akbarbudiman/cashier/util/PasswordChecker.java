package com.akbarbudiman.cashier.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordChecker {

	private String hashedPassword;
	private String plainPassword;
	
	public PasswordChecker setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
		return this;
	}
	
	public PasswordChecker setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
		return this;
	}
	
	public boolean check() {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}
	
	public PasswordChecker reset() {
		this.hashedPassword = null;
		this.plainPassword = null;
		return this;
	}
	
}
