package com.jb.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class Coupon {
    private final String description;
    private final Double percent;
}
