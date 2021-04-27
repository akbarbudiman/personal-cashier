package com.akbarbudiman.cashier.models.response;

import java.util.ArrayList;
import java.util.List;

import com.akbarbudiman.cashier.models.entity.Store;

public class StoreProductResponse extends StoreResponse {

	private List<ProductResponse> products = new ArrayList<ProductResponse>();

	public StoreProductResponse(Store entity) {
		super(entity);
	}
	
	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}

}
