package com.rereverb.advertisement.integration.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rereverb.advertisement.service.AdvertisementService;
import com.rereverb.api.order.rest.dto.OrderStatusChangedDto;
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
    public final AdvertisementService advertisementService;

    @KafkaListener(topics = "order-status")
    public void consume(String message) throws IOException {
        OrderStatusChangedDto dto = objectMapper.readValue(message, OrderStatusChangedDto.class);
        log.info(dto.toString());

        advertisementService.handleOrderStatusChanged(dto);
    }
}