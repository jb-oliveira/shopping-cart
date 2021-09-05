package com.jb.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderImputDTO {

    private String cpf;
    private List<PlaceOrderInputItemDTO> items;
    private String coupon;
    private String zipCode;
}
