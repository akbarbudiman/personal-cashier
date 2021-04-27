package com.akbarbudiman.cashier.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akbarbudiman.cashier.models.entity.Product;
import com.akbarbudiman.cashier.models.response.ProductStoreResponse;
import com.akbarbudiman.cashier.service.ProductService;

@RestController
@Validated
@RequestMapping("/products")
public class ProductController {

	@Autowired ProductService productService;
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductStoreResponse> getAllProduct() {
		List<Product> productList = productService.findAll();
		
		List<ProductStoreResponse> response = new ArrayList<ProductStoreResponse>();
		productList.forEach(p -> response.add(new ProductStoreResponse(p)));
		
		return response;
	}
	
	@GetMapping(value = "/{productid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductStoreResponse getCertainProduct(
		@PathVariable(value = "productid", required = true) Long productId
	) {
		Product product = productService.findById(productId);
		ProductStoreResponse response = new ProductStoreResponse(product);
		return response;
	}
	
	
	
}
