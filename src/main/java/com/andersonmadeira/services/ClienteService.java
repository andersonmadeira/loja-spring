package com.andersonmadeira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonmadeira.domain.Cliente;
import com.andersonmadeira.repositories.ClienteRepository;
import com.andersonmadeira.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> client = repo.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id = " + id
				+ ", Tipo: " + Cliente.class.getName()));
	}

}
