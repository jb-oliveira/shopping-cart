package com.jb.api.domain.repository;

import com.jb.api.domain.entity.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CouponRepository extends MongoRepository<Coupon, String> {
}
