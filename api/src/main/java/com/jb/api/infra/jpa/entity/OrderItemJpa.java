package com.jb.api.infra.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item", schema = "ccca")
public class OrderItemJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_order")
    private Long orderId;
    @Column(name = "id_item")
    private Long itemId;
    private Double price;
    private Integer quantity;
}
