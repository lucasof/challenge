package br.com.fiap.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity(name ="t_six_tipo_sensor")
public class TipoSensor {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome do sensor é obrigatório!")
	private String nome;
	
	@Size(min=15,message = "A descrição deve ter pelo menos 15 caracteres")
	private String descricao;
}
