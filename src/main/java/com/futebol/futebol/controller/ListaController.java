package com.futebol.futebol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.futebol.futebol.models.Jogador;
import com.futebol.futebol.models.Lista;
import com.futebol.futebol.repository.JogadorRepository;
import com.futebol.futebol.repository.ListaRepository;
import com.futebol.futebol.service.EnviaEmail;


@Controller
public class ListaController {
	@Autowired
	private ListaRepository listaRepository;
	@Autowired
	private JogadorRepository jogadorRepository;

	@RequestMapping(value = "/formLista", method = RequestMethod.GET)
	public String form() {
		return "formLista";
	}

	@RequestMapping(value = "/formLista", method = RequestMethod.POST)
	public String form(Lista lista) {
		listaRepository.save(lista);
		return "redirect:/formLista";
	}

	@RequestMapping("/lista")
	public ModelAndView lista() {
		ModelAndView model = new ModelAndView("/indexlista");
		Iterable<Lista> lista = listaRepository.findAll();
		model.addObject("lista",lista);
		return model;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView listaDetalhe(@PathVariable("id") long id) {
		Lista lista = listaRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("/detalheLista");
		modelAndView.addObject("lista", lista);

		Iterable<Jogador> jogadores = jogadorRepository.findByLista(lista);
		
		modelAndView.addObject("jogadores", jogadores);
		return modelAndView;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String listaDetalhes(@PathVariable("id") long id, Jogador jogador
) {
		Lista lista = listaRepository.findById(id);
		jogador.setLista(lista);
		jogadorRepository.save(jogador);
		
		new EnviaEmail().enviarEmail(jogador.getEmail(), jogador.getEmail());
		
		return "redirect:/{id}";
	}
	
	@RequestMapping("/deletarLista")
	public String deletarLista(long id) {
		 Lista lista = listaRepository.findById(id);
		 listaRepository.delete(lista);
		 return "redirect:/lista";
	}
	@RequestMapping("/deletarJogador")
	public String deletarJogador(String identidade) {
		 Jogador jogador = jogadorRepository.findByIdentidade(identidade);
		 jogadorRepository.delete(jogador);
		 
		 Lista lista = jogador.getLista();
		 long LogId = lista.getId();
		 String id = "" + LogId;
		 return "redirect:/" + id;
		 
	}
}