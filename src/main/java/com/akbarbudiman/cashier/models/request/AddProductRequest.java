package com.akbarbudiman.cashier.models.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddProductRequest {

	@NotNull(message = "Product name is mandatory")
	@Size(min = 6)
	private String name;

	@NotNull(message = "Product price is mandatory")
	@Min(value = 0)
	private BigDecimal price;

	@NotNull(message = "Product stock is mandatory")
	private Double stock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

}
