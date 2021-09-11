package com.jb.api.config;

import com.jb.api.domain.repository.CouponRepository;
import com.jb.api.domain.repository.ItemRepository;
import com.jb.api.domain.repository.OrderRepository;
import com.jb.api.infra.jpa.repository.CouponRepositoryJpa;
import com.jb.api.infra.jpa.repository.ItemRepositoryJpa;
import com.jb.api.infra.jpa.repository.OrderRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.jb.api.infra.jpa.repository")
public class DatabaseConfig {

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepositoryJpa();
    }

    @Bean
    public ItemRepository itemRepository() {
        return new ItemRepositoryJpa();
    }

    @Bean
    public CouponRepository couponRepository() {
        return new CouponRepositoryJpa();
    }
}
