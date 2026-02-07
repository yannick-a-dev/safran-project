package com.safran.safran_projet.service;

import com.safran.safran_projet.model.Engine;
import com.safran.safran_projet.repo.EngineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EngineService {

    private final EngineRepository engineRepository;

    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    public Engine createOrGetEngine(String serialNumber, String model, String location) {
        Optional<Engine> engineOpt = engineRepository.findBySerialNumber(serialNumber);
        if (engineOpt.isPresent()) return engineOpt.get();

        Engine engine = Engine.builder()
                .serialNumber(serialNumber)
                .model(model)
                .location(location)
                .createdAt(LocalDateTime.now())
                .build();
        return engineRepository.save(engine);
    }
}
