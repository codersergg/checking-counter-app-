package com.codersergg.checklimit.rabbitmq;

import com.codersergg.checklimit.CheckLimitService;
import com.codersergg.clients.checklimit.CheckLimitRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class CheckLimitConsumer {

    private final CheckLimitService checkLimitService;

    @RabbitListener(queues = "${rabbitmq.queue.check-limit}")
    public void consumer(CheckLimitRequest checkLimitRequest) {
        log.info("Consumed {} from queue", checkLimitRequest);
        checkLimitService.send(checkLimitRequest);
        System.out.println(checkLimitRequest);
    }
}
