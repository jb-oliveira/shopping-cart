package com.jb.api.infra.repository.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {
    @Id
    private Long id;
    private Long orderId;
    private Long itemId;
    private Double price;
    private Integer quantity;
}
