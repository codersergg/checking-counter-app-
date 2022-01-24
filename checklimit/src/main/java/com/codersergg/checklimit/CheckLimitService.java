package com.codersergg.checklimit;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CheckLimitService {

    private final CheckLimitHistoryRepository checkHistoryRepository;

    public CheckLimitService(CheckLimitHistoryRepository checkLimitHistoryRepository) {
        this.checkHistoryRepository = checkLimitHistoryRepository;
    }

    public boolean isCheckedCustomer(Integer customerId) {
        checkHistoryRepository.save(
                CheckLimitHistory.builder()
                        .customerId(customerId)
                        .isExceeded(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
