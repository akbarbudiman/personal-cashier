package com.akbarbudiman.cashier.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akbarbudiman.cashier.models.entity.Store;
import com.akbarbudiman.cashier.models.entity.User;
import com.akbarbudiman.cashier.models.entity.UserStoreRole;
import com.akbarbudiman.cashier.models.exception.UnauthorizedException;
import com.akbarbudiman.cashier.models.exception.UnauthorizedException.UnauthorizedReason;
import com.akbarbudiman.cashier.models.pojo.UserRole;

@Service
public class UserStoreRoleManager {
	
	@Autowired UserStoreRoleService userStoreRoleService;

	public boolean isAuthorizedToAddProduct(User user, Store store) {
		UserStoreRole userRole = userStoreRoleService.findUserStoreRole(user, store);
		return isAuthorizedToAddProduct(userRole);
	}
	
	public boolean isAuthorizedToAddProduct(UserStoreRole userRole) {
		if (authorizedRoleToAddProduct.contains(userRole.getRole())) return true;
		else throw new UnauthorizedException(UnauthorizedReason.INVALID_ROLE);
	}
	
	
	public boolean isAuthorizedToAddUser(User user, Store store) {
		UserStoreRole userRole = userStoreRoleService.findUserStoreRole(user, store);
		return isAuthorizedToAddUser(userRole);
	}
	
	public boolean isAuthorizedToAddUser(UserStoreRole userRole) {
		if (authorizedRoleToAddUser.contains(userRole.getRole())) return true;
		else throw new UnauthorizedException(UnauthorizedReason.INVALID_ROLE);
	}
	
	
	public boolean isAuthorizedToFindAllUserInStore(User user, Store store) {
		UserStoreRole userRole = userStoreRoleService.findUserStoreRole(user, store);
		return isAuthorizedToFindAllUserInStore(userRole);
	}
	
	public boolean isAuthorizedToFindAllUserInStore(UserStoreRole userRole) {
		if (authorizedRoleToFindAllUserInStore.contains(userRole.getRole())) return true;
		else throw new UnauthorizedException(UnauthorizedReason.INVALID_ROLE);
	}
	
	
	public boolean isAuthorizedToRemoveUserInStore(User user, Store store) {
		UserStoreRole userRole = userStoreRoleService.findUserStoreRole(user, store);
		return isAuthorizedToRemoveUserInStore(userRole);		
	}
	
	
	public boolean isAuthorizedToRemoveUserInStore(UserStoreRole userRole) {
		if (authorizedRoleToRemoveUserInStore.contains(userRole.getRole())) return true;
		else throw new UnauthorizedException(UnauthorizedReason.INVALID_ROLE);
	}

	public boolean isAuthorizedToAccessDesiredRole(UserStoreRole userRole, UserRole desiredRole) {
		return isAuthorizedToAccessDesiredRole(userRole.getRole(), desiredRole);
	}
	
	public boolean isAuthorizedToAccessDesiredRole(UserRole user, UserRole desiredRole) {
		List<UserRole> authorizedRoleToAccess = authorizedRoleToAccessDictionary.get(user);
		if (authorizedRoleToAccess.contains(desiredRole)) return true;
		else throw new UnauthorizedException(UnauthorizedReason.INVALID_ROLE);
	}
	
	
	public boolean isAuthorizedToUpdateStore(UserStoreRole userRole) {
		if (authorizedRoleToUpdateStore.contains(userRole.getRole())) return true;
		else throw new UnauthorizedException(UnauthorizedReason.INVALID_ROLE);
	}
	
	
	public boolean isAuthorizedToUpdateProduct(User user, Store store) {
		UserStoreRole userRole = userStoreRoleService.findUserStoreRole(user, store);
		return isAuthorizedToUpdateProduct(userRole);		
	}
	
	public boolean isAuthorizedToUpdateProduct(UserStoreRole userRole) {
		if (authorizedRoleToUpdateProduct.contains(userRole.getRole())) return true;
		else throw new UnauthorizedException(UnauthorizedReason.INVALID_ROLE);
	}



	private final List<UserRole> authorizedRoleToAddProduct = List.of(
			UserRole.OWNER,
			UserRole.EMPLOYEE
	);

	private final List<UserRole> authorizedRoleToAddUser = List.of(
			UserRole.OWNER
	);
	
	private final Map<UserRole, List<UserRole>> authorizedRoleToAccessDictionary = Map.of(
			UserRole.OWNER, 	List.of(UserRole.EMPLOYEE),
			UserRole.EMPLOYEE,	List.of()
	);

	private final List<UserRole> authorizedRoleToUpdateStore = List.of(
			UserRole.OWNER
	);
	
	private final List<UserRole> authorizedRoleToFindAllUserInStore = List.of(
			UserRole.OWNER
	);

	private final List<UserRole> authorizedRoleToRemoveUserInStore = List.of(
			UserRole.OWNER
	);
	
	private final List<UserRole> authorizedRoleToUpdateProduct = List.of(
			UserRole.OWNER,
			UserRole.EMPLOYEE
	);

}
