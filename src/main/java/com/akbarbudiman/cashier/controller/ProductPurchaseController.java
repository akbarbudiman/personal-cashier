package com.akbarbudiman.cashier.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akbarbudiman.cashier.models.entity.Product;
import com.akbarbudiman.cashier.models.entity.Purchase;
import com.akbarbudiman.cashier.models.entity.Session;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.request.PurchaseRequest;
import com.akbarbudiman.cashier.models.response.PurchaseResponse;
import com.akbarbudiman.cashier.service.ProductService;
import com.akbarbudiman.cashier.service.PurchaseService;
import com.akbarbudiman.cashier.service.SessionService;

@RestController
@Validated
@RequestMapping("/products")
public class ProductPurchaseController {

	@Autowired SessionService sessionService;
	@Autowired ProductService productService;
	@Autowired PurchaseService purchaseService;
	
	@PostMapping(value = "/{productid}/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
	public PurchaseResponse purchase (
		@RequestHeader(value = "session", required = true) String sessionId,
		@PathVariable(value = "productid", required = true) Long productId,
		@RequestBody @Valid PurchaseRequest request
	) {
		LocalDateTime requestTime = LocalDateTime.now();
		
		Session session = sessionService.validateSession(sessionId);
		User user = session.getUser();
		
		Double unitToPurchase = request.getUnitToPurchase();
		
		Product product = productService.findById(productId);
		productService.validateThatProductCanBePurchased(product, unitToPurchase);
		productService.reduceStock(product, unitToPurchase);
		
		Purchase purchaseData = purchaseService.createPurchase(user, product, requestTime);
		
		PurchaseResponse response = new PurchaseResponse(purchaseData);
		return response;
	}
	
}
