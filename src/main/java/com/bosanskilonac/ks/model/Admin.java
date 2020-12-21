package com.bosanskilonac.ks.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import enums.Role;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(unique=true)
	private String username;
	@NotBlank
	private String sifra;
	@NotNull
	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
