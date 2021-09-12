package br.com.fiap.model;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name ="t_six_funcionario")
@EqualsAndHashCode(callSuper=false)
public class Funcionario extends Pessoa{
	
	@NotBlank(message = "O registro profissional é obrigatório")
	private String registroProfissional;
	
	
	@Temporal(TemporalType.DATE)
	private Date dataAdmissão;
	
	@Min(value = 1000, message = "Salário mínimo de 1000 reais!")
	private float salario;
}
