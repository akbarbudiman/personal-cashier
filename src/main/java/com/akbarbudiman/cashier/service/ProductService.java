package com.akbarbudiman.cashier.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akbarbudiman.cashier.models.entity.Product;
import com.akbarbudiman.cashier.models.entity.Store;
import com.akbarbudiman.cashier.models.exception.ForbiddenException;
import com.akbarbudiman.cashier.models.exception.NotFoundException;
import com.akbarbudiman.cashier.models.exception.NotFoundException.Entity;
import com.akbarbudiman.cashier.models.request.AddProductRequest;
import com.akbarbudiman.cashier.models.request.UpdateProductRequest;
import com.akbarbudiman.cashier.repo.ProductRepo;

@Service
public class ProductService {

	@Autowired ProductRepo repo;

	public Product addProduct(Store store, @Valid AddProductRequest request) {
		Product product = new Product();
		product.setName(request.getName());
		product.setPrice(request.getPrice());
		product.setStock(request.getStock());
		product.setStore(store);
		repo.save(product);
		return product;
	}

	public Product findById(Long productId) {
		Optional<Product> entity = repo.findById(productId);
		if (entity.isPresent())
			return entity.get();
		else
			throw new NotFoundException(Entity.PRODUCT);
	}
	
	public List<Product> findAll() {
		return repo.findAll();
	}

	public Product updateProduct(Product product, @Valid UpdateProductRequest request) {
		if (request.getNewProductName() != null) {
			product.setName(request.getNewProductName());
		}

		if (request.getNewPrice() != null) {
			product.setPrice(request.getNewPrice());
		}

		if (request.getStockChange() != null) {
			Double oldStock = product.getStock();
			Double newStock = oldStock + request.getStockChange();
			product.setStock(newStock);
		}

		repo.save(product);
		return product;
	}

	public boolean validateThatProductCanBePurchased(Product product, Double unitToPurchase) {
		Double currentStock = product.getStock();
		boolean isStockEnough = currentStock > unitToPurchase;
		if (isStockEnough)
			return true;
		else
			throw new ForbiddenException(String.format("Stock is not enough, %d left.", currentStock));
	}

	public Product reduceStock(Product product, Double unitToPurchase) {
		UpdateProductRequest updateRequest = new UpdateProductRequest().withStockChange(unitToPurchase * -1);
		return updateProduct(product, updateRequest);
	}

}
