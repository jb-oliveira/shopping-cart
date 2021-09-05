package com.jb.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceOrderInputItemDTO {
    private String itemId;
    private int quantity;
}
