package com.jb.api.infra.repository.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class OrderEntity {
    @Id
    private Long id;
    private String cpf;
    private String couponId;
    private LocalDate issueDate;
    private Long sequence;

}
