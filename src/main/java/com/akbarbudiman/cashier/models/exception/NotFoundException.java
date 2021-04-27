package com.akbarbudiman.cashier.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5455569847040670290L;

	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(Entity entity) {
		String message = String.format("%s is not found", entity.toString());
		throw new NotFoundException(message);
	}
	
	public NotFoundException(Entity entity, Long id) {
		String message = String.format("%s with ID=%d is not found", entity.toString(), id);
		throw new NotFoundException(message);
	}
	
	public static enum Entity {
		USER,
		STORE,
		PRODUCT,
		USER_STORE_ROLE,
		TRANSACTION,
	}
	
}
