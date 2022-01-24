package com.codersergg.checklimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CheckLimitApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckLimitApplication.class, args);
    }
}
