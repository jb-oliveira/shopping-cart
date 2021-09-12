package com.jb.api.application.placeOrder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceOrderInputItemDTO {
    private Long itemId;
    private int quantity;
}
