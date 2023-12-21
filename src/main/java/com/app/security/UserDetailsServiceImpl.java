package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.persistence.crud.ClientRepository;
import com.app.persistence.entities.Client;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = clientRepository
			.findClientByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("El cliente con email " + email + " no existe"));
	
		return new UserDetailsImpl(client);
	}
}