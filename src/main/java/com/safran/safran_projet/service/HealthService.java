package com.safran.safran_projet.service;

import com.safran.safran_projet.model.Engine;
import com.safran.safran_projet.model.HealthStatus;
import com.safran.safran_projet.model.SensorMeasurement;
import com.safran.safran_projet.repo.SensorMeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthService {

    private final SensorMeasurementRepository measurementRepository;

    public HealthService(SensorMeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public HealthStatus calculateHealth(Engine engine) {
        List<SensorMeasurement> measurements = measurementRepository.findByEngineOrderByTimestampDesc(engine);

        double healthScore = 100.0;

        if (!measurements.isEmpty()) {
            SensorMeasurement latest = measurements.get(0);

            if (latest.getTemperature() > 90) healthScore -= 30;
            if (latest.getVibration() > 0.5) healthScore -= 30;
            if (latest.getPressure() > 10) healthScore -= 20;
        }

        String status;
        if (healthScore > 80) status = "OK";
        else if (healthScore > 50) status = "WARNING";
        else status = "CRITICAL";

        return HealthStatus.builder()
                .engineSerialNumber(engine.getSerialNumber())
                .healthScore(healthScore)
                .status(status)
                .build();
    }
}
