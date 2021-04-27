package com.akbarbudiman.cashier.models.response;

import java.util.ArrayList;
import java.util.List;

import com.akbarbudiman.cashier.models.entity.Store;

public class StoreUserResponse extends StoreResponse {

	public StoreUserResponse(Store entity) {
		super(entity);
	}

	private List<StoreUserSingleResponse> storeUser = new ArrayList<StoreUserSingleResponse>();

	public List<StoreUserSingleResponse> getStoreUser() {
		return storeUser;
	}

	public void setStoreUser(List<StoreUserSingleResponse> storeUser) {
		this.storeUser = storeUser;
	}

}
