package com.jb.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Setter
    private Long id;
    private final Long itemId;
    private final Double price;
    private final int quantity;

    public Double getTotal() {
        return this.price * this.quantity;
    }
}
