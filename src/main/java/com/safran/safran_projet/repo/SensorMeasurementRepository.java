package com.safran.safran_projet.repo;

import com.safran.safran_projet.model.Engine;
import com.safran.safran_projet.model.SensorMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorMeasurementRepository extends JpaRepository<SensorMeasurement, Long> {
    List<SensorMeasurement> findByEngineOrderByTimestampDesc(Engine engine);
}