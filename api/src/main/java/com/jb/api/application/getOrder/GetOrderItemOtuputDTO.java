package com.jb.api.application.getOrder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetOrderItemOtuputDTO {
    private String description;
    private Double price;
    private Integer quantity;
}
