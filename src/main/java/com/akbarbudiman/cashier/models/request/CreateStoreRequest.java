package com.akbarbudiman.cashier.models.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateStoreRequest {

	@NotNull
	@Size(min = 1)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
