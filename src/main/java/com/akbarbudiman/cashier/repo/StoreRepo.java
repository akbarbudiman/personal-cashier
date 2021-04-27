package com.akbarbudiman.cashier.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akbarbudiman.cashier.models.entity.Store;

public interface StoreRepo extends JpaRepository<Store, Long>{

}
