package com.akbarbudiman.cashier.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHash {

	private String plainPassword;
	
	public PasswordHash setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
		return this;
	}
	
	public String hash() {
		return new BCryptPasswordEncoder().encode(plainPassword);
	}
	
	public PasswordHash reset() {
		this.plainPassword = null;
		return this;
	}
	
	
	public static void main(String[] args) {
		String plainPassword = "plainPassword";
		PasswordHash ph = new PasswordHash();
		PasswordChecker pc = new PasswordChecker();
		
		String hashedPassword = ph
								.setPlainPassword(plainPassword)
								.hash();
		
		boolean checkPassword = pc
								.setHashedPassword(hashedPassword)
								.setPlainPassword(plainPassword)
								.check();
		
		System.out.println(checkPassword);
		
		
		String anotherHashedPassword = ph.hash();
		
		System.out.println("hashedPassword:\n" + hashedPassword);
		System.out.println("anotherHashedPassword:\n" + anotherHashedPassword);
		
		boolean checkUsingAnotherHashedPassword = pc
													.setHashedPassword(anotherHashedPassword)
													.check();
		
		System.out.println(checkUsingAnotherHashedPassword);
	}
}
