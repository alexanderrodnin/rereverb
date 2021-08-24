package com.rereverb.order.integration.kafka;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rereverb.api.advertisement.rest.dto.AdvertisementStatusChangedDto;
import com.rereverb.api.order.rest.dto.OrderStatusChangedDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private static final String TOPIC = "order-status";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void publishAdvertisementStatusChanged(OrderStatusChangedDto dto) {
        String message = objectMapper.writeValueAsString(dto);
        log.info(String.format("#### -> Producing message -> %s", message));

        this.kafkaTemplate.send(TOPIC, message);
    }
}
