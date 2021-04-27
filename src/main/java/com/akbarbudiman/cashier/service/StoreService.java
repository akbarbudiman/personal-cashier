package com.akbarbudiman.cashier.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akbarbudiman.cashier.models.entity.Product;
import com.akbarbudiman.cashier.models.entity.Store;
import com.akbarbudiman.cashier.models.exception.NotFoundException;
import com.akbarbudiman.cashier.models.exception.NotFoundException.Entity;
import com.akbarbudiman.cashier.models.request.CreateStoreRequest;
import com.akbarbudiman.cashier.repo.StoreRepo;

@Service
public class StoreService {

	@Autowired StoreRepo repo;

	public Store createStore(@Valid CreateStoreRequest request) {
		Store store = new Store();
		store.setName(request.getName());
		store.setProducts(new ArrayList<Product>());
		repo.save(store);
		return store;
	}
	
	public Store findStore(Long storeId) {
		Optional<Store> store = repo.findById(storeId);
		if (store.isPresent()) return store.get();
		else throw new NotFoundException(Entity.STORE);
	}
	
	public List<Store> findAllStore() {
		return repo.findAll();
	}

	public void addProduct(Store store, Product product) {
		store.getProducts().add(product);
		repo.save(store);
	}

	public void updateStore(Store store, @Valid CreateStoreRequest request) {
		store.setName(request.getName());
		repo.save(store);
	}

}
