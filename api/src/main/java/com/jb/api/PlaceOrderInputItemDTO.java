package com.jb.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceOrderInputItemDTO {
    private String description;
    private Double price;
    private int quantity;
}
