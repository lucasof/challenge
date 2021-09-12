package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.TipoSensor;

public interface TipoSensorRepository extends JpaRepository<TipoSensor, Long> {

}
