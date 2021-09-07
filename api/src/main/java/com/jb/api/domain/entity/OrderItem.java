package com.jb.api.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class OrderItem {
    @Setter
    private String id;
    private final String itemId;
    private final Double price;
    private final int quantity;

    public Double getTotal() {
        return this.price * this.quantity;
    }
}
