package com.akbarbudiman.cashier.models.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "PURCHASE")
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PURCHASE")
	@SequenceGenerator(name = "SEQ_PURCHASE", sequenceName = "SEQ_PURCHASE", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_purchase_user")
	private User buyer;

	@ManyToOne
	@JoinColumn(name = "fk_transaction_product")
	private Product product;

	@NotNull
	private LocalDateTime purchaseDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDateTime getPurchaseDateTime() {
		return purchaseDateTime;
	}

	public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
		this.purchaseDateTime = purchaseDateTime;
	}

}
