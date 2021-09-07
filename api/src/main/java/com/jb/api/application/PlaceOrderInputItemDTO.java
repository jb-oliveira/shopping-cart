package com.jb.api.application;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceOrderInputItemDTO {
    private String itemId;
    private int quantity;
}
