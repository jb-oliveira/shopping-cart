package com.jb.api.domain.repository;

import com.jb.api.domain.entity.Order;

public interface OrderRepository {

    Order save(Order order);
}
