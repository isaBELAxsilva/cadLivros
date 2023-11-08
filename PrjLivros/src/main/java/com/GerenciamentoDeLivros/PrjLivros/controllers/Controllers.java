package com.GerenciamentoDeLivros.PrjLivros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerenciamentoDeLivros.PrjLivros.entities.Livros;
import com.GerenciamentoDeLivros.PrjLivros.services.Service;


@RestController
@RequestMapping("/livros")
public class Controllers {
	
	private final Service service;

	@GetMapping("/home")
	public String paginaInicial() {
		return "index";
	}

	@Autowired
	public Controllers(Service service) {
		this.service = service;
	}

	@PostMapping
	public Livros createLivros(@RequestBody Livros livros) {
		return service.saveLivros(livros);
	}

	@DeleteMapping("/{id}")
	public void deleteLivros(@PathVariable Long id) {
		service.deleteLivros(id);
	}

	@GetMapping
	public ResponseEntity<List<Livros>> getAllLivros(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Livros> livros = service.getAllLivros();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(livros);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livros> getLivros(@PathVariable Long id) {
		Livros livros = service.getLivrosById(id);
		if (livros != null) {
			return ResponseEntity.ok(livros);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public Livros updateLivros(@PathVariable Long id, @RequestBody Livros livros) {
		return service.updateLivros(id, livros);
	}


}
