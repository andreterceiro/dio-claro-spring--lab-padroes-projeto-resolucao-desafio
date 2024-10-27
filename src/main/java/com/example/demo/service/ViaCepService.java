package com.example.demo.service;

import com.example.demo.model.Endereco;

public class ViaCepService {

	public Endereco consultarCep(String cep) {
		Endereco endereco = new Endereco();
		endereco.setBairro("Alto da mooca");
		endereco.setCep("03176-001");
		endereco.setComplemento("");
		endereco.setLogradouro("Rua Tobias Barreto");
		endereco.setUf("SP");
		endereco.setLocalidade("Cidade");
		return endereco;
	}
}
