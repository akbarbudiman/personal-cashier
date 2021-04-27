package com.akbarbudiman.cashier.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akbarbudiman.cashier.models.entity.Session;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.exception.UnauthorizedException;
import com.akbarbudiman.cashier.models.exception.UnauthorizedException.UnauthorizedReason;
import com.akbarbudiman.cashier.repo.SessionRepo;
import com.akbarbudiman.cashier.util.NullityChecker;

@Service
public class SessionService {
	
	@Autowired SessionRepo repo;
	@Autowired UserService userService;
	
	private static final int SESSION_DURATION_MINUTES = 120;

	public Session getUserSession(User user)  {
		Session session = user.getSession();
		
		if (session != null) {
			refreshSession(session);
		}
		else {
			session = new Session();
			session.setUser(user);
			session.setSessionExpiration(getExtendedSessionExpiration());
			repo.save(session);
			
			userService.udpateUserSession(user, session);
		}
		
		return session;
	}
	
	public Session validateSession(String sessionId) {
		Session session = findSession(sessionId);
		
		if (session != null) {
			refreshSession(session);
			return session;
		}
		else {
			throw new UnauthorizedException(UnauthorizedReason.INVALID_SESSION);
		}
	}
	
	private Session findSession(String sessionId) {
		List<Session> sessionFound = repo.findBySessionId(sessionId);
		boolean isSessionFound = NullityChecker.isListEmpty(sessionFound);
		if (isSessionFound) {
			return sessionFound.get(0);
		}
		else {
			return null;
		}
	}
	
	private void refreshSession(Session session) {
		session.setSessionExpiration(getExtendedSessionExpiration());
		repo.save(session);
	}
	
	
	private LocalDateTime getExtendedSessionExpiration() {
		return LocalDateTime.now().plusMinutes(SESSION_DURATION_MINUTES);
	}
	
}
