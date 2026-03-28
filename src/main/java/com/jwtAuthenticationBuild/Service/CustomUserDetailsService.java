package com.jwtAuthenticationBuild.Service;

import java.util.Optional;

import com.jwtAuthenticationBuild.Security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtAuthenticationBuild.Entity.User;
import com.jwtAuthenticationBuild.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	
	private final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> credential = repository.findByUsername(username);
		return credential.map(CustomUserDetails:: new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
	}
}
