package com.akbarbudiman.cashier.models.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PurchaseRequest {

	@NotNull
	@Min(1)
	private Double unitToPurchase;

	public Double getUnitToPurchase() {
		return unitToPurchase;
	}

	public void setUnitToPurchase(Double unitToPurchase) {
		this.unitToPurchase = unitToPurchase;
	}

}
