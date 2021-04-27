package com.akbarbudiman.cashier.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akbarbudiman.cashier.models.entity.Purchase;

public interface TransactionRepo extends JpaRepository<Purchase, Long> {

}
