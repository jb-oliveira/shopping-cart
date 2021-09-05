package com.jb.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlaceOrderImputDTO {

    private String cpf;
    private List<PlaceOrderInputItemDTO> items;
    private String coupon;
}
