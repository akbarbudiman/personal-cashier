package com.akbarbudiman.cashier.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akbarbudiman.cashier.models.entity.Session;
import com.akbarbudiman.cashier.models.entity.Store;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.entity.UserStoreRole;
import com.akbarbudiman.cashier.models.pojo.UserRole;
import com.akbarbudiman.cashier.models.request.CreateStoreRequest;
import com.akbarbudiman.cashier.models.response.StoreResponse;
import com.akbarbudiman.cashier.service.SessionService;
import com.akbarbudiman.cashier.service.StoreService;
import com.akbarbudiman.cashier.service.UserStoreRoleManager;
import com.akbarbudiman.cashier.service.UserStoreRoleService;

@RestController
@Validated
@RequestMapping("/stores")
public class StoreController {
	
	@Autowired StoreService storeService;
	@Autowired SessionService sessionService;
	@Autowired UserStoreRoleService userStoreRoleService;
	@Autowired UserStoreRoleManager userStoreRoleManager;

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public StoreResponse createStore(
		@RequestHeader(value = "session", required = true) String sessionId,
		@RequestBody @Valid CreateStoreRequest request
	) {
		Session session = sessionService.validateSession(sessionId);
		User user = session.getUser();
		
		Store store = storeService.createStore(request);
		
		userStoreRoleService.addUserStoreRole(user, store, UserRole.OWNER);
		
		StoreResponse response = new StoreResponse(store);
		return response;
	}
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StoreResponse> getAllStore(
		@RequestHeader(value = "session", required = true) String sessionId
	) {
		sessionService.validateSession(sessionId);
		
		List<Store> allStore = storeService.findAllStore();
		
		List<StoreResponse> response = new ArrayList<StoreResponse>();
		allStore.forEach(s -> response.add(new StoreResponse(s)));
		return response;
	}
	
	@GetMapping(value = "/{storeid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public StoreResponse getCertainStore(
		@RequestHeader(value = "session", required = true) String sessionId,
		@RequestHeader(value = "storeid", required = true) Long storeId
	) {
		sessionService.validateSession(sessionId);
		
		Store store = storeService.findStore(storeId);
		
		return new StoreResponse(store);
	}
	
	@PutMapping(value = "/{storeid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public StoreResponse updateStore(
		@RequestHeader(value = "session", required = true) String sessionId,
		@RequestHeader(value = "storeid", required = true) Long storeId,
		@RequestBody @Valid CreateStoreRequest request
	) {
		Session session = sessionService.validateSession(sessionId);
		User user = session.getUser();
		
		Store store = storeService.findStore(storeId);
		
		UserStoreRole userRole = userStoreRoleService.findUserStoreRole(user, store);
		
		userStoreRoleManager.isAuthorizedToUpdateStore(userRole);
		
		storeService.updateStore(store, request);
		
		return new StoreResponse(store);
	}
	
}
