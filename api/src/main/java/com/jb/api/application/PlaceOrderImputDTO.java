package com.jb.api.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
