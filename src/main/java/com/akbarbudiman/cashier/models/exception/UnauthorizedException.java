package com.akbarbudiman.cashier.models.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6841217684025930237L;

	public UnauthorizedException(String message) {
		super(message);
	}
	
	public UnauthorizedException(UnauthorizedReason reason) {
		String compiledMessage = "Unauthorized, because:".concat(this.message.get(reason));
		throw new UnauthorizedException(compiledMessage);
	}
	
	private final Map<UnauthorizedReason, String> message = Map.of(
		UnauthorizedReason.INCORRECT_PASSWORD, "incorrect password.",
		UnauthorizedReason.INVALID_SESSION, "invalid session. Please login.",
		UnauthorizedReason.INVALID_ROLE, "invalid role. You've no authorization for that"
	);
	
	public static enum UnauthorizedReason {
		INCORRECT_PASSWORD,
		INVALID_SESSION,
		INVALID_ROLE
	}
}
