package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.EquipeMedica;

public interface EquipeRepository extends JpaRepository<EquipeMedica, Long> {

}
