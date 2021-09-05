package com.jb.api;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CouponTest {

    @Test
    void shouldVerifyExpiredCoupon() {
        Coupon coupon = new Coupon("VALE20", 20.0, LocalDate.of(2020, 10, 10));
        assertTrue(coupon.isExpired());
    }
}
