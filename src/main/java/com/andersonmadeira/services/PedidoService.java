package com.andersonmadeira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonmadeira.domain.Pedido;
import com.andersonmadeira.repositories.PedidoRepository;
import com.andersonmadeira.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> cat = repo.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id = " + id
				+ ", Tipo: " + Pedido.class.getName()));
	}

}
