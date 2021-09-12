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

import br.com.fiap.model.Paciente;
import br.com.fiap.repository.PacienteRepository;

@RestController
@RequestMapping("/api/paciente")
public class ApiPacienteController {
	@Autowired
	private PacienteRepository repository;
	
	@GetMapping
	public Page<Paciente> index(@PageableDefault Pageable pageable) {
		return repository.findAll(pageable);
		
	}
	
	@PostMapping
	public ResponseEntity<Paciente> create(@RequestBody @Valid Paciente paciente,  UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder
				.path("/api/paciente/{id}")
				.buildAndExpand(paciente.getId())
				.toUri();
		repository.save(paciente);
		return ResponseEntity.created(uri).body(paciente);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Paciente> show(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Paciente> destroy(@PathVariable Long id){
		Optional<Paciente> paciente = repository.findById(id);
		
		if (paciente.isEmpty()) 
			return ResponseEntity.notFound().build();

		repository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Paciente> update(@PathVariable Long id,@RequestBody Paciente newPaciente){
		
		Optional<Paciente> optional = repository.findById(id);
		
		if (optional.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		Paciente paciente = optional.get();
		paciente.setNome(newPaciente.getNome());
		paciente.setDocumento(newPaciente.getDocumento());
		paciente.setTelefone(newPaciente.getTelefone());
		paciente.setEmail(newPaciente.getEmail());
		paciente.setEnfermidade(newPaciente.getEnfermidade());
		paciente.setDataEntrada(newPaciente.getDataEntrada());
		paciente.setQuarto(newPaciente.getQuarto());
		paciente.setResponsavel(newPaciente.getResponsavel());

		repository.save(paciente);

		
		return ResponseEntity.ok(paciente);
		
	}
}	
