package br.com.fiap.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.model.Funcionario;
import br.com.fiap.repository.FuncionarioRepository;

@RestController
@RequestMapping("/api/funcionario")
public class ApiFuncionarioController {
	@Autowired
	private FuncionarioRepository repository;
	
	@GetMapping
	public Page<Funcionario> index(@PageableDefault Pageable pageable) {
		return repository.findAll(pageable);
		
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> create(@RequestBody @Valid Funcionario funcionario,  UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder
				.path("/api/funcionario/{id}")
				.buildAndExpand(funcionario.getId())
				.toUri();
		repository.save(funcionario);
		return ResponseEntity.created(uri).body(funcionario);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Funcionario> show(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Funcionario> destroy(@PathVariable Long id){
		Optional<Funcionario> funcionario = repository.findById(id);
		
		if (funcionario.isEmpty()) 
			return ResponseEntity.notFound().build();

		repository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Funcionario> update(@PathVariable Long id,@RequestBody Funcionario newFuncionario){
		
		Optional<Funcionario> optional = repository.findById(id);
		
		if (optional.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		Funcionario funcionario = optional.get();
		funcionario.setNome(newFuncionario.getNome());
		funcionario.setDocumento(newFuncionario.getDocumento());
		funcionario.setTelefone(newFuncionario.getTelefone());
		funcionario.setEmail(newFuncionario.getEmail());
		funcionario.setRegistroProfissional(newFuncionario.getRegistroProfissional());
		funcionario.setDataAdmissão(newFuncionario.getDataAdmissão());
		funcionario.setSalario(newFuncionario.getSalario());

		repository.save(funcionario);

		
		return ResponseEntity.ok(funcionario);
		
	}
}	
