package com.jb.api.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Entity
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
