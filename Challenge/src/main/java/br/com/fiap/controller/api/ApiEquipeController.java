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

import br.com.fiap.model.EquipeMedica;
import br.com.fiap.repository.EquipeRepository;

@RestController
@RequestMapping("/api/equipeMedica")
public class ApiEquipeController {
	@Autowired
	private EquipeRepository repository;
	
	@GetMapping
	public Page<EquipeMedica> index(@PageableDefault Pageable pageable) {
		return repository.findAll(pageable);
		
	}
	
	@PostMapping
	public ResponseEntity<EquipeMedica> create(@RequestBody @Valid EquipeMedica equipeMedica,  UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder
				.path("/api/equipeMedica/{id}")
				.buildAndExpand(equipeMedica.getId())
				.toUri();
		repository.save(equipeMedica);
		return ResponseEntity.created(uri).body(equipeMedica);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<EquipeMedica> show(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<EquipeMedica> destroy(@PathVariable Long id){
		Optional<EquipeMedica> EquipeMedica = repository.findById(id);
		
		if (EquipeMedica.isEmpty()) 
			return ResponseEntity.notFound().build();

		repository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<EquipeMedica> update(@PathVariable Long id,@RequestBody EquipeMedica newEquipeMedica){
		
		Optional<EquipeMedica> optional = repository.findById(id);
		
		if (optional.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		EquipeMedica equipeMedica = optional.get();
		equipeMedica.setDataEntradaProfissional(newEquipeMedica.getDataEntradaProfissional());
		equipeMedica.setDataSaidaProfissional(newEquipeMedica.getDataSaidaProfissional());
		equipeMedica.setDataInicioTratamento(newEquipeMedica.getDataInicioTratamento());
		equipeMedica.setDataTerminoTratamento(newEquipeMedica.getDataTerminoTratamento());
		equipeMedica.setPaciente(newEquipeMedica.getPaciente());
		equipeMedica.setFuncionario(newEquipeMedica.getFuncionario());


		repository.save(equipeMedica);

		
		return ResponseEntity.ok(equipeMedica);
		
	}
}	
