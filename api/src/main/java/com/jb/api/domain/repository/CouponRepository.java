package com.jb.api.domain.repository;

import com.jb.api.domain.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository {
    Optional<Coupon> findById(String id);

    Coupon save(Coupon coupon);
}
