package com.futebol.futebol.repository;

import org.springframework.data.repository.CrudRepository;

import com.futebol.futebol.models.Jogador;
import com.futebol.futebol.models.Lista;


public interface JogadorRepository extends CrudRepository<Jogador, String> {

	Iterable<Jogador> findByLista(Lista	lista);
	Jogador findByIdentidade (String identidade);

}
