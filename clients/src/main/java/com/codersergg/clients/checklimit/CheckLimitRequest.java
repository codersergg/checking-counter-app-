package com.codersergg.clients.checklimit;

public record CheckLimitRequest(
        Integer toCustomerId,
        Integer valueOfService,
        String toServiceName,
        String createdAt,
        String message) {
}
