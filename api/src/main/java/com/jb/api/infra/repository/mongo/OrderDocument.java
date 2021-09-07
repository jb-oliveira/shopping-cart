package com.jb.api.infra.repository.mongo;

import com.jb.api.domain.entity.Coupon;
import com.jb.api.domain.entity.Cpf;
import com.jb.api.domain.entity.OrderItem;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class OrderDocument {
    @Id
    private String id;
    private String cpf;
    private String couponId;
    private LocalDate issueDate;
    private Long sequence;

}
