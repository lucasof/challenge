package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
