package com.jb.api.infra.jpa.repository;

import com.jb.api.infra.jpa.entity.CouponJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepositoryTemplate extends JpaRepository<CouponJpa, String> {

}
