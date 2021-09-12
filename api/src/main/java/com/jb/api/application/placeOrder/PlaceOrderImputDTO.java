package com.jb.api.application.placeOrder;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PlaceOrderImputDTO {

    private String cpf;
    private List<PlaceOrderInputItemDTO> items;
    private String coupon;
    private String zipCode;
    private LocalDate issueDate;
}
