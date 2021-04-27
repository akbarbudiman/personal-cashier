package com.akbarbudiman.cashier.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akbarbudiman.cashier.models.entity.Session;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.exception.ForbiddenException;
import com.akbarbudiman.cashier.models.request.RegisterRequest;
import com.akbarbudiman.cashier.models.response.UserResponse;
import com.akbarbudiman.cashier.service.SessionService;
import com.akbarbudiman.cashier.service.UserService;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

	@Autowired UserService userService;

	@Autowired SessionService sessionService;
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponse register(@Valid @RequestBody RegisterRequest request) {
		boolean isPasswordConsistent = request.getPassword().equals(request.getConfirmedPassword());
		if (isPasswordConsistent == false) {
			throw new ForbiddenException("Inconsistent password");
		}

		boolean doesUserNameFreeToUse = userService.isUserNameUnique(request.getUserName());
		if (doesUserNameFreeToUse == false) {
			throw new ForbiddenException("Username has been used");
		}

		UserResponse createdUser = userService.createUser(request);
		return createdUser;
	}

	@GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponse getMySelfInfo(
		@RequestHeader(value = "session", required = true) String sessionId
	) {
		Session session = sessionService.validateSession(sessionId);
		User user = session.getUser();
		
		return new UserResponse(user);
	}

}
