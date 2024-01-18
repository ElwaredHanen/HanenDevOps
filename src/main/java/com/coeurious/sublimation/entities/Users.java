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
import lombok.Data;

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
	private Instant creationDate;
	private Instant modificationDate;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(joinColumns=@JoinColumn(name="users_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
	private List<Role> roles;
}
