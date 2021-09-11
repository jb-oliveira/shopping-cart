package com.jb.api.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class Coupon {
    @Id
    private final String code;
    private final Double percent;
    private final LocalDate expirationDate;

    public boolean isExpired() {
        LocalDate today = LocalDate.now();
        return today.isAfter(this.expirationDate);
    }
}
