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

import br.com.fiap.model.TipoSensor;
import br.com.fiap.repository.TipoSensorRepository;

@RestController
@RequestMapping("/api/tipoSensor")
public class ApiTipoSensorController {
	@Autowired
	private TipoSensorRepository repository;
	
	@GetMapping
	public Page<TipoSensor> index(@PageableDefault Pageable pageable) {
		return repository.findAll(pageable);
		
	}
	
	@PostMapping
	public ResponseEntity<TipoSensor> create(@RequestBody @Valid TipoSensor tipoSensor,  UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder
				.path("/api/tipoSensor/{id}")
				.buildAndExpand(tipoSensor.getId())
				.toUri();
		repository.save(tipoSensor);
		return ResponseEntity.created(uri).body(tipoSensor);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TipoSensor> show(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<TipoSensor> destroy(@PathVariable Long id){
		Optional<TipoSensor> tipoSensor = repository.findById(id);
		
		if (tipoSensor.isEmpty()) 
			return ResponseEntity.notFound().build();

		repository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TipoSensor> update(@PathVariable Long id,@RequestBody TipoSensor newTipoSensor){
		
		Optional<TipoSensor> optional = repository.findById(id);
		
		if (optional.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		TipoSensor tipoSensor = optional.get();
		tipoSensor.setNome(newTipoSensor.getNome());
		tipoSensor.setDescricao(newTipoSensor.getDescricao());

		repository.save(tipoSensor);

		
		return ResponseEntity.ok(tipoSensor);
		
	}
}	
