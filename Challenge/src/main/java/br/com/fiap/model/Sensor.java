package br.com.fiap.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Sensor {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Min(value = 1000, message = "Salário mínimo de 1000 reais!")
	private float valor;
	
	@NotBlank(message="Data de admissão é obrigatória!")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataMedicao;
	
	@ManyToOne
	private TipoSensor tipo;

	@ManyToOne
	private Pulseira pulseira;
}
