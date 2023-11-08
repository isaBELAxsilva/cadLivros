package com.GerenciamentoDeLivros.PrjLivros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.GerenciamentoDeLivros.PrjLivros.entities.Livros;
import com.GerenciamentoDeLivros.PrjLivros.repositories.Repository;


@org.springframework.stereotype.Service
public class Service {
	
	private final Repository repository;
	
	@Autowired
	public Service(Repository repository) {
		this.repository = repository;
	}
	
	public Livros getLivrosById(Long Id) {
		return repository.findById(Id).orElse(null);
	}
	
	public List<Livros> getAllLivros(){
		return repository.findAll();
	}
	
	public Livros saveLivros(Livros livros) {
		return repository.save(livros);
	}
	
	public void deleteLivros(Long id) {
		repository.deleteById(id);
	}
	
	public Livros updateLivros(Long id, Livros novoLivro) {
        Optional<Livros> livroOptional = repository.findById(id);
        if (livroOptional.isPresent()) {
        	Livros livroExistente = livroOptional.get();
           	livroExistente.setDescricao(novoLivro.getDescricao());
        	livroExistente.setIsbn(novoLivro.getIsbn());          
            return repository.save(livroExistente); 
        } else {
            return null; 
        }
    }

}
