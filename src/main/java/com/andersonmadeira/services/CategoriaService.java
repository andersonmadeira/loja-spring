package com.andersonmadeira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonmadeira.domain.Categoria;
import com.andersonmadeira.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> cat = repo.findById(id);
		return cat.orElse(null);
	}

}
