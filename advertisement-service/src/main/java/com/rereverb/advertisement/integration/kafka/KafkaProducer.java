package com.rereverb.advertisement.integration.kafka;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rereverb.api.advertisement.rest.dto.AdvertisementStatusChangedDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private static final String TOPIC = "advertisement-status";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void publishAdvertisementStatusChanged(AdvertisementStatusChangedDto dto) {
        String message = objectMapper.writeValueAsString(dto);
        log.info(String.format("#### -> Producing message -> %s", message));

        this.kafkaTemplate.send(TOPIC, message);
    }
}
