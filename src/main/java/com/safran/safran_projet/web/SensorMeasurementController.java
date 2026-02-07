package com.safran.safran_projet.web;

import com.safran.safran_projet.dto.SensorMeasurementRequest;
import com.safran.safran_projet.model.Engine;
import com.safran.safran_projet.model.SensorMeasurement;
import com.safran.safran_projet.service.EngineService;
import com.safran.safran_projet.service.SensorMeasurementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/measurements")
public class SensorMeasurementController {

    private final SensorMeasurementService measurementService;
    private final EngineService engineService;

    public SensorMeasurementController(SensorMeasurementService measurementService,
                                       EngineService engineService) {
        this.measurementService = measurementService;
        this.engineService = engineService;
    }

    @PostMapping
    public ResponseEntity<SensorMeasurement> postMeasurement(@RequestBody SensorMeasurementRequest request) {

        // Crée ou récupère le moteur
        Engine engine = engineService.createOrGetEngine(
                request.getEngineSerialNumber(),
                request.getEngineModel(),
                request.getEngineLocation()
        );

        // Crée la mesure
        SensorMeasurement measurement = SensorMeasurement.builder()
                .engine(engine)
                .temperature(request.getTemperature())
                .vibration(request.getVibration())
                .pressure(request.getPressure())
                .timestamp(request.getTimestamp())
                .build();

        SensorMeasurement saved = measurementService.saveMeasurement(measurement);
        return ResponseEntity.ok(saved);
    }
}