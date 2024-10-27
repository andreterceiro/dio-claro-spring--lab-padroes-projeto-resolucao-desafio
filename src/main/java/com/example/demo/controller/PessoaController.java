package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pessoa;
import com.example.demo.service.PessoaService;

@RestController
@RequestMapping("pessoas")
public class PessoaController {
	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public ResponseEntity<Iterable<Pessoa>> buscarTodos() {
		return ResponseEntity.ok(pessoaService.buscarTodos());
	}

	@PostMapping	
	public ResponseEntity<Pessoa> inserir(@RequestBody Pessoa pessoa) {
		pessoaService.inserir(pessoa);

		// Demonstration of singleton - please see the console
		System.out.println(pessoaService);

		return ResponseEntity.ok(pessoa);
	}
}