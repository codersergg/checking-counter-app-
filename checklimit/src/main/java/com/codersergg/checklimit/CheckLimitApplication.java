package com.codersergg.checklimit;

import com.codersergg.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.codersergg.checklimit",
                "com.codersergg.amqp"
        }
)
@EnableEurekaClient
public class CheckLimitApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckLimitApplication.class, args);
    }

    /*@Bean
    CommandLineRunner commandLineRunner(
            RabbitMQMessageProducer producer,
            CheckLimitConfig checkLimitConfig
    ) {
        return args -> {
            producer.publish(
                    "foo",
                    checkLimitConfig.getInternalExchange(),
                    checkLimitConfig.getCheckLimitRoutingKey()
            );
        };
    }*/
}
