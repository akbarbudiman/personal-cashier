package com.akbarbudiman.cashier.models.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SESSION")
public class Session {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String sessionId;

	@NotNull
	private LocalDateTime sessionExpiration;

	@NotNull
	@OneToOne
	private User user;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public LocalDateTime getSessionExpiration() {
		return sessionExpiration;
	}

	public void setSessionExpiration(LocalDateTime sessionExpiration) {
		this.sessionExpiration = sessionExpiration;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
