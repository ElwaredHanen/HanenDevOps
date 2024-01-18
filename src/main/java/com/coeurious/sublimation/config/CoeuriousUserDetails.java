package com.coeurious.sublimation.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.repository.UsersRepository;

@Service
public class CoeuriousUserDetails implements UserDetailsService{
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String email, password = null;
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		Users users = usersRepository.findByMail(username);
		email = users.getMail();
		password = users.getPassword();
		
		users.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
		
		return new User(email, password, authorities);
	}
	
}
