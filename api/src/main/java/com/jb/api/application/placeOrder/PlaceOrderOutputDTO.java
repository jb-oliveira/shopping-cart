package com.jb.api.application.placeOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderOutputDTO {
    private String code;
    private Double total;
    private Double freight;
}
