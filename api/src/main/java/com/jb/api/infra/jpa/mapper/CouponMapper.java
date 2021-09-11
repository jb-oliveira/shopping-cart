package com.jb.api.infra.jpa.mapper;

import com.jb.api.domain.entity.Coupon;
import com.jb.api.infra.jpa.entity.CouponJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    Coupon fromJpa(CouponJpa jpa);

    CouponJpa fromCoupon(Coupon jpa);
}
