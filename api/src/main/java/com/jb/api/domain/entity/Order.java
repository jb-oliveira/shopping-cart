package com.jb.api.domain.entity;

import com.jb.api.domain.exception.DomainException;
import com.jb.api.domain.exception.InvalidCouponException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    @Setter
    private String id;
    private Cpf cpf;
    private List<OrderItem> items;
    private Coupon coupon;
    private LocalDate issueDate;
    private String code;
    private Double freight;
    private Long sequence;

    public Order(String cpf, Long sequence) {
        this.cpf = new Cpf(cpf);
        this.items = new ArrayList<>();
        this.issueDate = LocalDate.now();
        this.sequence = sequence;
        this.freight = 0.0;
    }

    public void addItem(String id, Double price, int quantity) {
        this.items.add(new OrderItem(id, price, quantity));
    }

    public void validate() throws DomainException {
        cpf.validate();
        if (this.coupon != null && coupon.isExpired()) {
            throw new InvalidCouponException("Expired coupon");
        }
    }

    public Double getTotal() {
        double total = this.items.stream().mapToDouble(OrderItem::getTotal).sum();
        if (coupon != null) {
            total -= (total * coupon.getPercent()) / 100;
        }
        total += this.freight;
        return total;
    }

    public void addCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public void addFreight(Double freight) {
        this.freight += freight;
    }

    public Double getFreight() {
        return freight;
    }

    public String getCode() {
        return String.format("%d%09d", this.issueDate.getYear(), this.sequence);
    }
}

