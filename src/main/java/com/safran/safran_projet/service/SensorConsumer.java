package com.safran.safran_projet.service;

import com.safran.safran_projet.model.Engine;
import com.safran.safran_projet.model.SensorMeasurement;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class SensorConsumer {

    private final SensorMeasurementService measurementService;
    private final EngineService engineService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SensorConsumer(SensorMeasurementService measurementService, EngineService engineService) {
        this.measurementService = measurementService;
        this.engineService = engineService;
    }

    @KafkaListener(topics = "sensor-measurements", groupId = "sensor-group")
    public void consume(String message) {
        try {
            SensorMeasurement measurement = objectMapper.readValue(message, SensorMeasurement.class);

            // On crée ou récupère un moteur fictif
            Engine engine = engineService.createOrGetEngine("ENG-001", "Turbofan-X", "Hangar A");
            measurement.setEngine(engine);

            measurementService.saveMeasurement(measurement);
            System.out.println("Message consommé et sauvegardé : " + measurement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
