package com.jb.api.config;

import com.jb.api.domain.repository.OrderRepository;
import com.jb.api.infra.repository.jpa.OrderRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public OrderRepository orderRepository(){
        return new OrderRepositoryJpa();
    }
}