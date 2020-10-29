package com.paraparp.gestorfondos.service.imp;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserEntityService userEntityService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userEntityService.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
	}

	public UserDetails loadUserById(Long id) throws UsernameNotFoundException{

		return userEntityService.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User with ID: " + id + " not found."));
	}

}
