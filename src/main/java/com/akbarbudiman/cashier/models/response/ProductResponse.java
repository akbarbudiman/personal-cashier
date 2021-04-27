package com.akbarbudiman.cashier.models.response;

import java.math.BigDecimal;

import com.akbarbudiman.cashier.models.entity.Product;

public class ProductResponse {

	private Long id;
	private String name;
	private BigDecimal price;
	private Double stock;

	public ProductResponse(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.price = entity.getPrice();
		this.stock = entity.getStock();
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
