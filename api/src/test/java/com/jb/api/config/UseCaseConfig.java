package com.jb.api.config;

import com.jb.api.application.PlaceOrder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class UseCaseConfig {
    @Bean
    PlaceOrder placeOrder() {
        return new PlaceOrder();
    }
}
