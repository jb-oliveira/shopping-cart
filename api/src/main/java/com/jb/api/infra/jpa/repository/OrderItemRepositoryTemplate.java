package com.jb.api.infra.jpa.repository;

import com.jb.api.infra.jpa.entity.OrderItemJpa;
import com.jb.api.infra.jpa.entity.OrderJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepositoryTemplate extends JpaRepository<OrderItemJpa, Long> {

}
