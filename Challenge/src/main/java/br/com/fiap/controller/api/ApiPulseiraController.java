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

import br.com.fiap.model.Pulseira;
import br.com.fiap.repository.PulseiraRepository;

@RestController
@RequestMapping("/api/pulseira")
public class ApiPulseiraController {
	@Autowired
	private PulseiraRepository repository;
	
	@GetMapping
	public Page<Pulseira> index(@PageableDefault Pageable pageable) {
		return repository.findAll(pageable);
		
	}
	
	@PostMapping
	public ResponseEntity<Pulseira> create(@RequestBody @Valid Pulseira pulseira,  UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder
				.path("/api/pulseira/{id}")
				.buildAndExpand(pulseira.getId())
				.toUri();
		repository.save(pulseira);
		return ResponseEntity.created(uri).body(pulseira);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pulseira> show(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Pulseira> destroy(@PathVariable Long id){
		Optional<Pulseira> Pulseira = repository.findById(id);
		
		if (Pulseira.isEmpty()) 
			return ResponseEntity.notFound().build();

		repository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pulseira> update(@PathVariable Long id,@RequestBody Pulseira newPulseira){
		
		Optional<Pulseira> optional = repository.findById(id);
		
		if (optional.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		Pulseira pulseira = optional.get();
		pulseira.setPaciente(newPulseira.getPaciente());

		repository.save(pulseira);

		
		return ResponseEntity.ok(pulseira);
		
	}
}	
