package com.andersonmadeira;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andersonmadeira.domain.Categoria;
import com.andersonmadeira.repositories.CategoriaRepository;

@SpringBootApplication
public class LojaSpringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;

	public static void main(String[] args) {
		SpringApplication.run(LojaSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		catRepo.saveAll(Arrays.asList(cat1, cat2));
	}

}
