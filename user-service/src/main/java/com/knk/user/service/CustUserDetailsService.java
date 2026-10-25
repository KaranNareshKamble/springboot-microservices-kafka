package com.knk.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.knk.user.entity.User;
import com.knk.user.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustUserDetailsService implements UserDetailsService {
	
	private final UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User u= userRepo.findByUserName(username).orElseThrow(()->
		 new UsernameNotFoundException("User Not Found"));
		return org.springframework.security.core.userdetails.User
				.withUsername(u.getUserName())
				.password(u.getPassword())
				.authorities("USER")
				.build();
	}

}
