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

import br.com.fiap.model.Sensor;
import br.com.fiap.repository.SensorRepository;

@RestController
@RequestMapping("/api/sensor")
public class ApiSensorController {
	@Autowired
	private SensorRepository repository;
	
	@GetMapping
	public Page<Sensor> index(@PageableDefault Pageable pageable) {
		return repository.findAll(pageable);
		
	}
	
	@PostMapping
	public ResponseEntity<Sensor> create(@RequestBody @Valid Sensor sensor,  UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder
				.path("/api/sensor/{id}")
				.buildAndExpand(sensor.getId())
				.toUri();
		repository.save(sensor);
		return ResponseEntity.created(uri).body(sensor);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Sensor> show(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Sensor> destroy(@PathVariable Long id){
		Optional<Sensor> Sensor = repository.findById(id);
		
		if (Sensor.isEmpty()) 
			return ResponseEntity.notFound().build();

		repository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Sensor> update(@PathVariable Long id,@RequestBody Sensor newSensor){
		
		Optional<Sensor> optional = repository.findById(id);
		
		if (optional.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		Sensor sensor = optional.get();
		sensor.setValor(newSensor.getValor());
		sensor.setDataMedicao(newSensor.getDataMedicao());
		sensor.setTipo(newSensor.getTipo());
		sensor.setPulseira(newSensor.getPulseira());

		repository.save(sensor);

		
		return ResponseEntity.ok(sensor);
		
	}
}	
