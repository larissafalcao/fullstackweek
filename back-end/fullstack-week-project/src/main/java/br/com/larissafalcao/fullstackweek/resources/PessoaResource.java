package br.com.larissafalcao.fullstackweek.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.larissafalcao.fullstackweek.domain.Pessoa;
import br.com.larissafalcao.fullstackweek.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = { "http://localhost:3000"})
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa> listarTodos(){
		return pessoaRepository.findAll();
	}
	
	@PostMapping
	public Pessoa cadastrarPessoa (@RequestBody Pessoa pessoa) {
		Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
		pessoaCadastrada.setIsVacinada(false);
		return pessoaRepository.save(pessoaCadastrada);
	}
	
	@PutMapping("/{codigo}")
	public Pessoa atualizar(@PathVariable("codigo") Long codigo,
			@RequestBody Pessoa pessoa) {
		return pessoaRepository.findById(codigo).map(
				record -> {
					record.setCpf(pessoa.getCpf());
					record.setDataNascimento(pessoa.getDataNascimento());
					record.setEmail(pessoa.getEmail());
					record.setIdade(pessoa.getIdade());
					record.setNome(pessoa.getNome());
					record.setTelefone(pessoa.getTelefone());
					record.setIsVacinada(pessoa.getIsVacinada());
					return pessoaRepository.save(record);
				}).orElse(null);
	}
	
	@GetMapping("/{codigo}")
	public Pessoa buscarPeloCodigo(@PathVariable Long codigo) {
		return pessoaRepository.findById(codigo).orElse(null);
	}

}
