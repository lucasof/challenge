package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

}
