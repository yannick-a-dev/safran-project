package com.safran.safran_projet.service;

import com.safran.safran_projet.model.Alert;
import com.safran.safran_projet.model.Engine;
import com.safran.safran_projet.model.SensorMeasurement;
import com.safran.safran_projet.repo.AlertRepository;
import com.safran.safran_projet.repo.SensorMeasurementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SensorMeasurementService {

    private final SensorMeasurementRepository measurementRepository;
    private final AlertRepository alertRepository;

    public SensorMeasurementService(SensorMeasurementRepository measurementRepository,
                                    AlertRepository alertRepository) {
        this.measurementRepository = measurementRepository;
        this.alertRepository = alertRepository;
    }

    public SensorMeasurement saveMeasurement(SensorMeasurement measurement) {
        SensorMeasurement saved = measurementRepository.save(measurement);
        checkForAlerts(saved);
        return saved;
    }

    // Simple rules d’alerte
    private void checkForAlerts(SensorMeasurement measurement) {
        Engine engine = measurement.getEngine();

        if (measurement.getTemperature() > 90) {
            createAlert(engine, "TEMPERATURE_HIGH", "Température trop élevée: " + measurement.getTemperature());
        }
        if (measurement.getVibration() > 0.5) {
            createAlert(engine, "VIBRATION_HIGH", "Vibration trop élevée: " + measurement.getVibration());
        }
        if (measurement.getPressure() > 10) {
            createAlert(engine, "PRESSURE_HIGH", "Pression trop élevée: " + measurement.getPressure());
        }
    }

    private void createAlert(Engine engine, String type, String message) {
        Alert alert = Alert.builder()
                .engine(engine)
                .type(type)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
        alertRepository.save(alert);
    }
}
