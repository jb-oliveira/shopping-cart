package com.jb.api.application.getOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GetOrderOutputDTO {
    private String code;
    private Double freight;
    private Double total;
    List<GetOrderItemOtuputDTO> items;
    private Double taxes;
}
