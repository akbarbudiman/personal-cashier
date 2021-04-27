package com.akbarbudiman.cashier.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akbarbudiman.cashier.models.entity.Session;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.exception.NotFoundException;
import com.akbarbudiman.cashier.models.exception.NotFoundException.Entity;
import com.akbarbudiman.cashier.models.exception.UnauthorizedException;
import com.akbarbudiman.cashier.models.exception.UnauthorizedException.UnauthorizedReason;
import com.akbarbudiman.cashier.models.request.LoginRequest;
import com.akbarbudiman.cashier.models.response.SessionResponse;
import com.akbarbudiman.cashier.service.SessionService;
import com.akbarbudiman.cashier.service.UserService;
import com.akbarbudiman.cashier.util.PasswordChecker;

@RestController
@Validated
@RequestMapping("/sessions")
public class SessionController {

	@Autowired SessionService sessionService;
	@Autowired UserService userService;
	@Autowired PasswordChecker passwordChecker;
	
	@PostMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
	public SessionResponse login(@Valid @RequestBody LoginRequest request) {
		User user = userService.findByUserName(request.getUserName());
		boolean isUserFound = user != null;
		if (isUserFound == false) {
			throw new NotFoundException(Entity.USER);
		}
		
		boolean isPasswordCorrect = passwordChecker.setHashedPassword(user.getPassword())
													.setPlainPassword(request.getPassword())
													.check();
		if (isPasswordCorrect == false) {
			throw new UnauthorizedException(UnauthorizedReason.INCORRECT_PASSWORD);
		}
		
		Session session = sessionService.getUserSession(user);
		SessionResponse response = new SessionResponse(user, session);
		return response;
	}
	
}
