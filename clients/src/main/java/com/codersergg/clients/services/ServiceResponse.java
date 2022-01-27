package com.codersergg.clients.services;

public record ServiceResponse(
        Integer toCustomerId,
        Integer valueOfService,
        String toServiceName,
        String createdAt,
        String message) {
}
