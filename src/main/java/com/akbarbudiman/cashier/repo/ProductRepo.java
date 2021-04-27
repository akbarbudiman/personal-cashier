package com.akbarbudiman.cashier.repo;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.akbarbudiman.cashier.models.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Product> findById(Long id);
	
}
