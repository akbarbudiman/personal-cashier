package com.akbarbudiman.cashier.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akbarbudiman.cashier.models.entity.Product;
import com.akbarbudiman.cashier.models.entity.Purchase;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.exception.NotFoundException;
import com.akbarbudiman.cashier.models.exception.NotFoundException.Entity;
import com.akbarbudiman.cashier.repo.TransactionRepo;

@Service
public class PurchaseService {

	@Autowired TransactionRepo repo;
	
	public Purchase createPurchase(User buyer, Product product, LocalDateTime purchaseDateTime) {
		Purchase purchase = new Purchase();
		purchase.setBuyer(buyer);
		purchase.setProduct(product);
		purchase.setPurchaseDateTime(purchaseDateTime);
		repo.save(purchase);
		return purchase;
	}
	
	public Purchase findById(Long transactionId) {
		Optional<Purchase> entity = repo.findById(transactionId);
		if (entity.isPresent()) return entity.get();
		else throw new NotFoundException(Entity.TRANSACTION);
	}
	
}
