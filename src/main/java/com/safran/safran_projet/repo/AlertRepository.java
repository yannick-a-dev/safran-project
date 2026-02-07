package com.safran.safran_projet.repo;

import com.safran.safran_projet.model.Alert;
import com.safran.safran_projet.model.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByEngineOrderByTimestampDesc(Engine engine);
}
