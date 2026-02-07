package com.safran.safran_projet.service;

import com.safran.safran_projet.model.SensorMeasurement;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class SensorProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Random random = new Random();

    private final String topic = "sensor-measurements";

    public SensorProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 5000) // toutes les 5 secondes
    public void sendMeasurement() {
        try {
            SensorMeasurement measurement = SensorMeasurement.builder()
                    .temperature(70 + random.nextDouble() * 40) // 70 à 110
                    .vibration(0 + random.nextDouble())         // 0 à 1
                    .pressure(5 + random.nextDouble() * 10)     // 5 à 15
                    .timestamp(LocalDateTime.now())
                    .build();

            String message = objectMapper.writeValueAsString(measurement);
            kafkaTemplate.send(topic, message);
            System.out.println("Message envoyé : " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
