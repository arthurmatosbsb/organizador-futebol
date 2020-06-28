package com.futebol.futebol.repository;

import org.springframework.data.repository.CrudRepository;

import com.futebol.futebol.models.Lista;

public interface ListaRepository extends CrudRepository<Lista, String> {

	Lista findById(long id);
	
}
