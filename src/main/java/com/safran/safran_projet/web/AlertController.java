package com.safran.safran_projet.web;

import com.safran.safran_projet.model.Alert;
import com.safran.safran_projet.model.Engine;
import com.safran.safran_projet.repo.AlertRepository;
import com.safran.safran_projet.repo.EngineRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlertController {

    private final AlertRepository alertRepository;
    private final EngineRepository engineRepository;

    public AlertController(AlertRepository alertRepository, EngineRepository engineRepository) {
        this.alertRepository = alertRepository;
        this.engineRepository = engineRepository;
    }

    @GetMapping("/alerts")
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    // Récupère les alertes pour un moteur spécifique
    @GetMapping("/engines/{id}/alerts")
    public List<Alert> getAlertsByEngine(@PathVariable Long id) {
        Engine engine = engineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Engine not found"));
        return alertRepository.findByEngineOrderByTimestampDesc(engine);
    }
}
