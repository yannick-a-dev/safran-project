package com.safran.safran_projet.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HealthStatus {

    private String engineSerialNumber;
    private Double healthScore;
    private String status;
}

