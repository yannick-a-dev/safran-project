package com.safran.safran_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorMeasurementRequest {

    private String engineSerialNumber;
    private String engineModel;
    private String engineLocation;

    private Double temperature;
    private Double vibration;
    private Double pressure;

    private LocalDateTime timestamp;
}
