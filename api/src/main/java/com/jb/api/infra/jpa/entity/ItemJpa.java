package com.jb.api.infra.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "item", schema = "ccca")
public class ItemJpa {
    @Id
    private Long id;
    private String description;
    private Double price;
    private int width;
    private int height;
    private int length;
    private Double weight;

}
