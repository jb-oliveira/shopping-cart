package com.jb.api.infra.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "order", schema = "ccca")
public class OrderJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String cpf;

    @Column(name = "coupon_code")
    private String couponId;

    private LocalDate issueDate;

    @Column(name = "serial")
    private Long sequence;

    private Double freight;


}
