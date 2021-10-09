package com.jb.api.infra.jpa.repository;

import com.jb.api.domain.entity.Order;
import com.jb.api.infra.jpa.entity.ItemJpa;
import com.jb.api.infra.jpa.entity.OrderJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderJpa, Long> {
    OrderJpa findBycode(String code);
}
