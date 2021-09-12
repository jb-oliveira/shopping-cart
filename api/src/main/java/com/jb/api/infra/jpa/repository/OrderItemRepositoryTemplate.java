package com.jb.api.infra.jpa.repository;

import com.jb.api.infra.jpa.entity.OrderItemJpa;
import com.jb.api.infra.jpa.entity.OrderJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepositoryTemplate extends JpaRepository<OrderItemJpa, Long> {

    List<OrderItemJpa> findByOrderId(Long orderId);
}
