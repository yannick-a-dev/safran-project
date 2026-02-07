package com.safran.safran_projet.repo;


import com.safran.safran_projet.model.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EngineRepository extends JpaRepository<Engine, Long> {
    Optional<Engine> findBySerialNumber(String serialNumber);
}
