package com.jb.api.config;

import com.jb.api.domain.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServicesConfig {

    @Bean
    public OrderService orderService() {
        return new OrderService();
    }
}
