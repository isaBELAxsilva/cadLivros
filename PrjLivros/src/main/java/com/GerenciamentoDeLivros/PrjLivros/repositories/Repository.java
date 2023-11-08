package com.GerenciamentoDeLivros.PrjLivros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GerenciamentoDeLivros.PrjLivros.entities.Livros;

public interface Repository extends JpaRepository<Livros, Long> {

}
