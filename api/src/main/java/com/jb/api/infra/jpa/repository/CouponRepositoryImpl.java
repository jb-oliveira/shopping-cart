package com.jb.api.infra.jpa.repository;

import com.jb.api.domain.entity.Coupon;
import com.jb.api.domain.repository.CouponRepository;
import com.jb.api.infra.jpa.entity.CouponJpa;
import com.jb.api.infra.jpa.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CouponRepositoryImpl implements CouponRepository {

    @Autowired
    private CouponRepositoryJpa template;

    @Autowired
    private CouponMapper mapper;

    @Override
    public Optional<Coupon> findById(String id) {
        CouponJpa jpa = template.findById(id).orElse(null);
        return Optional.ofNullable(mapper.fromJpa(jpa));
    }

    @Override
    public Coupon save(Coupon coupon) {
        CouponJpa saved = template.save(mapper.fromCoupon(coupon));
        return mapper.fromJpa(saved);
    }
}
