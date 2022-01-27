package com.codersergg.service1;

import com.codersergg.amqp.RabbitMQMessageProducer;
import com.codersergg.clients.services.ServiceResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class Service1Service {

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void sendMessage2CheckLimit(ServiceResponse serviceResponse) {
        rabbitMQMessageProducer.publish(
                serviceResponse,
                "internal.exchange",
                "internal.check-limit.routing-key"
        );
    }
}
