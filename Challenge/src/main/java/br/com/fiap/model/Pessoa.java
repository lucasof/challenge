package br.com.fiap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity(name ="t_six_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Pessoa {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O título é obrigatório")
	private String nome;
	
	@Size(min=9, message = "Documento deve ter no mínimo 9 caracteres")
	private String documento;
	
	@Size(min=10, message = "Telefone deve ter no mínimo 10 caracteres")
	private String telefone;
	
	@Pattern(regexp = "[a-zA-Z0-9]{1,}[@]{1}[a-z]{5,}[.]{1}+[a-z]{3}",message = "E-mail inválido")
	private String email;
}
