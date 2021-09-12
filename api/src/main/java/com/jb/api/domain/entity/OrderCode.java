package com.jb.api.domain.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderCode {
    private String value;

    public OrderCode(LocalDate issueDate, Long sequence) {
        this.value = String.format("%d%09d", issueDate.getYear(), sequence);
    }
}
