package com.akbarbudiman.cashier.models.response;

import java.time.LocalDateTime;

import com.akbarbudiman.cashier.models.entity.Purchase;

public class PurchaseResponse {

	private Long id;
	private UserResponse buyer;
	private ProductResponse product;
	private LocalDateTime purchaseDateTime;

	public PurchaseResponse(Purchase entity) {
		this.id = entity.getId();
		this.buyer = new UserResponse(entity.getBuyer());
		this.product = new ProductResponse(entity.getProduct());
		this.purchaseDateTime = entity.getPurchaseDateTime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserResponse getBuyer() {
		return buyer;
	}

	public void setBuyer(UserResponse buyer) {
		this.buyer = buyer;
	}

	public ProductResponse getProduct() {
		return product;
	}

	public void setProduct(ProductResponse product) {
		this.product = product;
	}

	public LocalDateTime getPurchaseDateTime() {
		return purchaseDateTime;
	}

	public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
		this.purchaseDateTime = purchaseDateTime;
	}

}
