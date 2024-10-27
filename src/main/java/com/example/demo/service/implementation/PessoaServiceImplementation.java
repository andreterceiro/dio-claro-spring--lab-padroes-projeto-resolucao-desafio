package com.example.demo.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pessoa;
import com.example.demo.model.PessoaRepository;
import com.example.demo.model.Endereco;
import com.example.demo.model.EnderecoRepository;
import com.example.demo.service.PessoaService;
import com.example.demo.service.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link PessoaService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author falvojr
 */
@Service
public class PessoaServiceImplementation implements PessoaService {
//
	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	//@Autowired
	private ViaCepService viaCepService;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Pessoa> buscarTodos() {
		// Buscar todos os Clientes.
		return pessoaRepository.findAll();
	}

	@Override
	public void inserir(Pessoa pessoa) {
		salvarPessoaComCep(pessoa);
	}

	private void salvarPessoaComCep(Pessoa pessoa) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		String cep = pessoa.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			viaCepService = new ViaCepService();
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		pessoa.setEndereco(endereco);
		// Inserir Cliente, vinculando o Endereco (novo ou existente).
		pessoaRepository.save(pessoa);
	}
}
