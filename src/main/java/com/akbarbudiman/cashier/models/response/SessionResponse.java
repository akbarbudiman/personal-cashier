package com.akbarbudiman.cashier.models.response;

import java.time.LocalDateTime;

import com.akbarbudiman.cashier.models.entity.Session;
import com.akbarbudiman.cashier.models.entity.User;

public class SessionResponse {

	private Long userId;
	private String userName;
	private String sessionId;
	private LocalDateTime sessionExpiration;
	
	public SessionResponse(User user, Session session) {
		this.userId = user.getId();
		this.userName = user.getUserName();
		this.sessionId = session.getSessionId();
		this.sessionExpiration = session.getSessionExpiration();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

}
