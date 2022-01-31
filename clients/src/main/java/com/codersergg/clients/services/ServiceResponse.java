package com.codersergg.clients.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public record ServiceResponse(
        Integer toCustomerId,
        Integer valueOfService,
        String toServiceName,
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime createdAt,
        String message) {
}
