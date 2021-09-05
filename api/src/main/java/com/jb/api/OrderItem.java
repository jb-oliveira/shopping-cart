package com.jb.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class OrderItem {
    private final String description;
    private final Double price;
    private final int quantity;

    public Double getTotal(){
        return this.price * this.quantity;
    }
}
