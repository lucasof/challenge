package br.com.fiap.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class EquipeMedica {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Data de admissão é obrigatória!")
	@Temporal(TemporalType.DATE)
	private Date dataEntradaProfissional;
	
	@Temporal(TemporalType.DATE)
	private Date dataSaidaProfissional;
	
	@NotBlank(message="Data de admissão é obrigatória!")
	@Temporal(TemporalType.DATE)
	private Date dataInicioTratamento;
	
	@Temporal(TemporalType.DATE)
	private Date dataTerminoTratamento;
	
	@ManyToOne
	private Paciente paciente;
	
	@ManyToOne
	private Funcionario funcionario;
	
}
