package com.akbarbudiman.cashier.models.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.akbarbudiman.cashier.models.pojo.UserRole;

@Entity
@Table(name = "USER_STORE_ROLE")
public class UserStoreRole {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERSTOREROLE")
	@SequenceGenerator(name = "SEQ_USERSTOREROLE", sequenceName = "SEQ_USERSTOREROLE", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_role_user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "fk_role_store")
	private Store store;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UserRole role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
