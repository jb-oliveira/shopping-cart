package com.jb.api.domain.repository;

import com.jb.api.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findByCode(String code);
}
