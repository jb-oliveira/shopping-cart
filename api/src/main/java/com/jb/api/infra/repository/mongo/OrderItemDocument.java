package com.jb.api.infra.repository.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDocument {
    @Id
    private String id;
    private String orderId;
    private String itemId;
    private Double price;
    private Integer quantity;
}
