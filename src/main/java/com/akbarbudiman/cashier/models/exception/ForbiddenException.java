package com.akbarbudiman.cashier.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4941871870423074925L;

	public ForbiddenException(String message) {
		super(message);
	}
	
}
