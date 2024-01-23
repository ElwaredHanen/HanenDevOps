package com.coeurious.sublimation.entities;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.generator.Generator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Users {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (unique=true)
	private String mail;
	
	private String password;
	
	private Boolean enabled;
	
	@Column(name = "creation_date")
	private Instant creationDate;
	
	@Column(name = "modification_date")
	private Instant modificationDate;
	
	@Column(name = "cgu_consent")
	private Boolean cguConsent;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cgu_id", nullable = false)
	private Cgu cgu;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(joinColumns=@JoinColumn(name="users_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
	private List<Role> roles;
}
