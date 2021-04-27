package com.akbarbudiman.cashier.models.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class UpdateProductRequest {

	@Size(min = 6)
	private String newProductName;

	@Min(value = 0)
	private BigDecimal newPrice;

	private Double stockChange;

	public UpdateProductRequest withStockChange(Double stockChange) {
		this.stockChange = stockChange;
		return this;
	}

	public String getNewProductName() {
		return newProductName;
	}

	public void setNewProductName(String newProductName) {
		this.newProductName = newProductName;
	}

	public BigDecimal getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}

	public Double getStockChange() {
		return stockChange;
	}

	public void setStockChange(Double stockChange) {
		this.stockChange = stockChange;
	}

}
