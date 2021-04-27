package com.akbarbudiman.cashier.models.response;

import com.akbarbudiman.cashier.models.entity.Store;

public class StoreResponse {

	private Long id;
	private String name;
	
	public StoreResponse(Store entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
