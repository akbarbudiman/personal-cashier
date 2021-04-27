package com.akbarbudiman.cashier.models.response;

import com.akbarbudiman.cashier.models.entity.Product;

public class ProductStoreResponse extends ProductResponse {

	private Long storeId;

	public ProductStoreResponse(Product entity) {
		super(entity);
		this.storeId = entity.getStore().getId();
	}
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

}
