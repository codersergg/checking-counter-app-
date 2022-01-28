package com.codersergg.checklimit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckLimitConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queue.check-limit}")
    private String checkLimitQueue;

    @Value("${rabbitmq.routing-keys.internal-check-limit}")
    private String internalCheckLimitRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue checkLimitQueue() {
        return new Queue(this.checkLimitQueue);
    }

    @Bean
    public Binding internalToCheckLimitBinding() {
        return BindingBuilder
                .bind(checkLimitQueue())
                .to(internalTopicExchange())
                .with(this.internalCheckLimitRoutingKey);
    }

    public String getInternalExchange() {
        return internalExchange;
    }

    public String getCheckLimitQueue() {
        return checkLimitQueue;
    }

    public String getCheckLimitRoutingKey() {
        return internalCheckLimitRoutingKey;
    }
}
