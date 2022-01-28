package com.codersergg.checklimit;

import com.codersergg.clients.checklimit.CheckLimitRequest;
import com.codersergg.clients.checklimit.UsedLimitResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CheckLimitService {

    private final CheckLimitRepository checkLimitRepository;

    public UsedLimitResponse getUsedLimitCustomer(Integer customerId) {
        log.info("isCheckedCustomer");
        final LocalDateTime ldt = LocalDateTime.now().minusSeconds(10);
        List<CheckLimitHistory> allByCustomerId = checkLimitRepository.findCheckLimitHistoriesByCustomerId(customerId, ldt);
        log.info("allByCustomerId: {}", allByCustomerId);
        Integer limit = allByCustomerId.stream().map(CheckLimitHistory::getValueOfService).reduce(0, Integer::sum);

        return new UsedLimitResponse(customerId, limit);
    }

    public void send(CheckLimitRequest checkLimitRequest) {
        log.info("send");
        LocalDateTime createdAt = LocalDateTime.parse(checkLimitRequest.createdAt());
        checkLimitRepository.save(
                CheckLimitHistory.builder()
                        .customerId(checkLimitRequest.toCustomerId())
                        .valueOfService(checkLimitRequest.valueOfService())
                        .serviceName(checkLimitRequest.toServiceName())
                        .createdAt(createdAt)
                        .message(checkLimitRequest.message())
                        .build()
        );
    }
}
