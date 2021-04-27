package com.akbarbudiman.cashier.utilities;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.akbarbudiman.cashier.util.PasswordChecker;
import com.akbarbudiman.cashier.util.PasswordHash;

public class PasswordUtilitiesTest {

	public void testPasswordUtilities() {
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
		
		assertTrue(checkPassword);
		
		
		String anotherHashedPassword = ph.hash();
		
		assertNotEquals(anotherHashedPassword, hashedPassword);
		
		boolean checkUsingAnotherHashedPassword = pc
													.setHashedPassword(anotherHashedPassword)
													.check();
		
		assertTrue(checkUsingAnotherHashedPassword);
		
	}
	
}
