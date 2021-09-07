package com.jb.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Document
public class Coupon {
    @Id
    private final String id;
    private final Double percent;
    private final LocalDate expiredDate;

    public boolean isExpired() {
        LocalDate today = LocalDate.now();
        return today.isAfter(this.expiredDate);
    }
}
