package com.akbarbudiman.cashier.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akbarbudiman.cashier.models.entity.Session;
import com.akbarbudiman.cashier.models.entity.Store;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.entity.UserStoreRole;
import com.akbarbudiman.cashier.models.exception.NotFoundException;
import com.akbarbudiman.cashier.models.exception.NotFoundException.Entity;
import com.akbarbudiman.cashier.models.pojo.UserRole;
import com.akbarbudiman.cashier.models.request.AddUserToStoreRequest;
import com.akbarbudiman.cashier.models.response.StoreUserResponse;
import com.akbarbudiman.cashier.models.response.StoreUserSingleResponse;
import com.akbarbudiman.cashier.models.response.UserStoreRoleResponse;
import com.akbarbudiman.cashier.service.ProductService;
import com.akbarbudiman.cashier.service.SessionService;
import com.akbarbudiman.cashier.service.StoreService;
import com.akbarbudiman.cashier.service.UserService;
import com.akbarbudiman.cashier.service.UserStoreRoleManager;
import com.akbarbudiman.cashier.service.UserStoreRoleService;

@RestController
@Validated
@RequestMapping("/stores")
public class StoreUserController {

	@Autowired StoreService storeService;
	@Autowired SessionService sessionService;
	@Autowired UserStoreRoleService userStoreRoleService;
	@Autowired UserStoreRoleManager userStoreRoleManager;
	@Autowired ProductService productService;
	@Autowired UserService userService;
	
	@PostMapping(value = "/{storeid}/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserStoreRoleResponse addUserToStore (
		@RequestHeader(value = "session", required = true) String sessionId,
		@PathVariable(value = "storeid", required = true) Long storeId,
		@RequestBody @Valid AddUserToStoreRequest request
	) {
		Session session = sessionService.validateSession(sessionId);
		User user = session.getUser();
		
		Store store = storeService.findStore(storeId);
		
		UserRole desiredRole = request.getRole();
		UserStoreRole userRole = userStoreRoleService.findUserStoreRole(user, store);
		
		userStoreRoleManager.isAuthorizedToAddUser(userRole);
		userStoreRoleManager.isAuthorizedToAccessDesiredRole(userRole, desiredRole);
		
		User userToAdd = userService.findByUserName(request.getUserName());
		if (userToAdd == null) throw new NotFoundException(Entity.USER);
		
		UserStoreRole addedUserStoreRole = userStoreRoleService.addUserStoreRole(userToAdd, store, desiredRole);
		
		UserStoreRoleResponse response = new UserStoreRoleResponse(addedUserStoreRole);
		return response;
	}
	
	@GetMapping(value="/{storeid}/users", produces=MediaType.APPLICATION_JSON_VALUE)
	public StoreUserResponse findAllUserInStore (
		@RequestHeader(value = "session", required = true) String sessionId,
		@PathVariable(value = "storeid", required = true) Long storeId
	) {
		Session session = sessionService.validateSession(sessionId);
		User user = session.getUser();
		
		Store store = storeService.findStore(storeId);
		
		userStoreRoleManager.isAuthorizedToFindAllUserInStore(user, store);
		
		List<UserStoreRole> entities = userStoreRoleService.findAllUserStoreRoleInStore(store);
		
		StoreUserResponse response = new StoreUserResponse(store);
		entities.forEach(userRole -> response.getStoreUser().add(new StoreUserSingleResponse(userRole)));
		
		return response;
	}
	
	@DeleteMapping(value = "/{storeid}/users/{userid}")
	public ResponseEntity<Object>  removeCertainUserInStore (
		@RequestHeader(value = "session", required = true) String sessionId,
		@PathVariable(value = "storeid", required = true) Long storeId,
		@PathVariable(value = "userid", required = true) Long userIdToRemove
	) {
		Session session = sessionService.validateSession(sessionId);
		User user = session.getUser();
		
		Store store = storeService.findStore(storeId);
		
		userStoreRoleManager.isAuthorizedToRemoveUserInStore(user, store);
		
		User userToRemove = userService.findByUserId(userIdToRemove);
		UserStoreRole userStoreRole = userStoreRoleService.findUserStoreRole(userToRemove, store);
		
		userStoreRoleService.remove(userStoreRole);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
