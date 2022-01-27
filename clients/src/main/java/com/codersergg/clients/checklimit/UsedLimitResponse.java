package com.codersergg.clients.checklimit;

import java.util.List;

public record UsedLimitResponse(
        Integer customerId,
        Integer limit) {
}
