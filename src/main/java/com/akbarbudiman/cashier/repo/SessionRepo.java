package com.akbarbudiman.cashier.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akbarbudiman.cashier.models.entity.Session;

public interface SessionRepo extends JpaRepository<Session, String> {

	public List<Session> findBySessionId(String sessionId);
	
}
