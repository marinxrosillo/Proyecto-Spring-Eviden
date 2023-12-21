package com.app.persistence.crud;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.persistence.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Optional<Client> findClientByName(String name);
	
	Optional<Client> findClientByEmail(String email);
	
	/*
	public List<Client> findAll() {
		return (List<Client>) clientCrudRepository.findAll();
	}
	
	public Optional<Client> findById(long id) {
		return (Optional<Client>) clientCrudRepository.findById(id);
	}
	
	public Client save(Client client) {
		return clientCrudRepository.save(client);
	}
	
	public void deleteById(long id) {
		clientCrudRepository.deleteById(id);
	}
	*/
}