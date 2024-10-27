package com.example.demo.service;

import com.example.demo.model.Pessoa;

public interface PessoaService {

	Iterable<Pessoa> buscarTodos();

	void inserir(Pessoa cliente);
}

