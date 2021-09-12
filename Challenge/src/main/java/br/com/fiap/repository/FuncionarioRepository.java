package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
