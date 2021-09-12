package br.com.fiap.model;


import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name ="t_six_paciente")
@EqualsAndHashCode(callSuper=false)
public class Paciente extends Pessoa{
		
	@NotBlank(message = "A enfermidade do paciente é obrigatória!")
	private String enfermidade;
	
	@NotBlank(message="Data de entrada é obrigatória!")
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	
	@Min(value = 100, message = "Não existem quartos com números inferiores a 100")
	private int quarto;
	
	@OneToOne
	@NotBlank(message="O paciente precisa ter um responsável!")
	private Pessoa responsavel;
}
