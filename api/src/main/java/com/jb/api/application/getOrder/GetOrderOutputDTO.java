package com.jb.api.application.getOrder;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetOrderOutputDTO {
    private String code;
    private Double freight;
    private Double total;
    List<GetOrderItemOtuputDTO> items;
}
