package com.akbarbudiman.cashier.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akbarbudiman.cashier.models.entity.Product;
import com.akbarbudiman.cashier.models.entity.Session;
import com.akbarbudiman.cashier.models.entity.Store;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.exception.NotFoundException;
import com.akbarbudiman.cashier.models.exception.NotFoundException.Entity;
import com.akbarbudiman.cashier.models.request.AddProductRequest;
import com.akbarbudiman.cashier.models.request.UpdateProductRequest;
import com.akbarbudiman.cashier.models.response.ProductResponse;
import com.akbarbudiman.cashier.models.response.StoreProductResponse;
import com.akbarbudiman.cashier.service.ProductService;
import com.akbarbudiman.cashier.service.SessionService;
import com.akbarbudiman.cashier.service.StoreService;
import com.akbarbudiman.cashier.service.UserStoreRoleManager;

@RestController
@Validated
@RequestMapping("/stores")
public class StoreProductController {

	@Autowired StoreService storeService;
	@Autowired SessionService sessionService;
	@Autowired UserStoreRoleManager userStoreRoleManager;
	@Autowired ProductService productService;
	
	@PostMapping(value = "/{storeid}/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public StoreProductResponse addProduct(
		@RequestHeader(value = "session", required = true) String sessionId,
		@PathVariable(value = "storeid", required = true) Long storeId,
		@RequestBody @Valid AddProductRequest request
	) {
		Session session = sessionService.validateSession(sessionId);
		User user = session.getUser();
		
		Store store = storeService.findStore(storeId);
		
		userStoreRoleManager.isAuthorizedToAddProduct(user, store);
		
		Product product = productService.addProduct(store, request);
		
		storeService.addProduct(store, product);
		
		StoreProductResponse response = new StoreProductResponse(store);
		response.getProducts().add(new ProductResponse(product));
		return response;
	}
	
	@GetMapping(value = "/{storeid}/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public StoreProductResponse getAllProductInCertainStore(
		@RequestHeader(value = "session", required = true) String sessionId,
		@PathVariable(value = "storeid", required = true) Long storeId
	) {
		sessionService.validateSession(sessionId);
		
		Store store = storeService.findStore(storeId);
		
		List<Product> products = store.getProducts();
		
		StoreProductResponse response = new StoreProductResponse(store);
		products.forEach(p -> response.getProducts().add(new ProductResponse(p)));
		return response;
	}
	
	@GetMapping(value = "/{storeid}/products/{productid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public StoreProductResponse getCertainProductInCertainStore(
		@RequestHeader(value = "session", required = true) String sessionId,
		@PathVariable(value = "storeid", required = true) Long storeId,
		@PathVariable(value = "productid", required = true) Long productId
	) {
		sessionService.validateSession(sessionId);
		
		Store store = storeService.findStore(storeId);
		
		List<Product> products = store.getProducts();
		
		Optional<Product> selectedProduct = products.stream().filter(p -> p.getId().equals(productId)).findFirst();
		
		if (selectedProduct.isPresent()) {
			StoreProductResponse response = new StoreProductResponse(store);
			response.getProducts().add(new ProductResponse(selectedProduct.get()));
			return response;
		}
		else {
			throw new NotFoundException(Entity.PRODUCT);
		}
			
	}
	
	@PatchMapping(value = "/{storeid}/products/{productid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public StoreProductResponse updateCertainProduct(
		@RequestHeader(value = "session", required = true) String sessionId,
		@PathVariable(value = "storeid", required = true) Long storeId,
		@PathVariable(value = "productid", required = true) Long productId,
		@RequestBody @Valid UpdateProductRequest request
	) {
		Session session = sessionService.validateSession(sessionId);
		User user = session.getUser();
		
		Store store = storeService.findStore(storeId);
		
		userStoreRoleManager.isAuthorizedToUpdateProduct(user, store);
		
		List<Product> products = store.getProducts();
		
		Optional<Product> oldProduct = products.stream().filter(p -> p.getId().equals(productId)).findFirst();
		
		if (oldProduct.isPresent()) {
			Product updatedProduct = productService.updateProduct(oldProduct.get(), request);
			StoreProductResponse response = new StoreProductResponse(store);
			response.getProducts().add(new ProductResponse(updatedProduct));
			return response;
		}
		else {
			throw new NotFoundException(Entity.PRODUCT);
		}
	}
	
}
