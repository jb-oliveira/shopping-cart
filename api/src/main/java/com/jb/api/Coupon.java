package com.jb.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class Coupon {
    private final String description;
    private final Double percent;
    private final LocalDate expiredDate;

    public boolean isExpired() {
        LocalDate today = LocalDate.now();
        return today.isAfter(this.expiredDate);
    }
}
