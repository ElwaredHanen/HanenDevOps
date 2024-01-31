package com.coeurious.sublimation.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Users implements UserDetails {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (unique=true)
	private String email;
	
	private String password;
	
	private Boolean enabled = true;

	private String role = "USER";
	
	@Column(name = "creation_date")
	private Instant creationDate;
	
	@Column(name = "modification_date")
	private Instant modificationDate;
	
	@Column(name = "cgu_consent")
	private Boolean cguConsent;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cgu_id", nullable = false)
	private Cgu cgu;


	public Users(){

	}
	public Users(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Users(String email, String password, Instant creationDate, Instant modificationDate) {
		this.email = email;
		this.password = password;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (RoleEnum.ADMIN.getValue().equals(this.role)) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		}
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

