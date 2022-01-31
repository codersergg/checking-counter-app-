package com.codersergg.clients.checklimit;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

public record CheckLimitRequest(
        Integer toCustomerId,
        Integer valueOfService,
        String toServiceName,
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        LocalDateTime createdAt,
        String message) {
}
