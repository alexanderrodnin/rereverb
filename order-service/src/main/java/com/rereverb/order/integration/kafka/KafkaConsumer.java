package com.rereverb.order.integration.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rereverb.api.advertisement.rest.dto.AdvertisementStatusChangedDto;
import com.rereverb.order.service.OrderStatusSagaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    public final ObjectMapper objectMapper;
    public final OrderStatusSagaService orderStatusSagaService;

    @KafkaListener(topics = "advertisement-status")
    public void consume(String message) throws IOException {
        AdvertisementStatusChangedDto dto = objectMapper.readValue(message, AdvertisementStatusChangedDto.class);
        log.info(dto.toString());

        orderStatusSagaService.handleAdvertisementStatusChanged(dto);
    }
}