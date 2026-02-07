package com.safran.safran_projet.web;

import com.safran.safran_projet.model.Engine;
import com.safran.safran_projet.model.HealthStatus;
import com.safran.safran_projet.repo.EngineRepository;
import com.safran.safran_projet.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/engines")
public class EngineController {

    private final EngineRepository engineRepository;
    private final HealthService healthService;

    public EngineController(EngineRepository engineRepository, HealthService healthService) {
        this.engineRepository = engineRepository;
        this.healthService = healthService;
    }

    @GetMapping("/{id}/health")
    public HealthStatus getEngineHealth(@PathVariable Long id) {
        Engine engine = engineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Engine not found"));
        return healthService.calculateHealth(engine);
    }
}