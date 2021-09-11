package com.jb.api.infra.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(schema = "ccca", name = "coupon")
@Data
@NoArgsConstructor
public class CouponJpa {
    @Id
    private String code;
    @Column(name = "percentage")
    private Double percent;
    @Column(name = "expire_date")
    private LocalDate expirationDate;

}
