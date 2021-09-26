package com.jb.api.domain.entity;

import com.jb.api.domain.exception.BaseException;
import com.jb.api.domain.exception.InvalidCouponException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    @Setter
    private Long id;
    private Cpf cpf;
    private List<OrderItem> items;
    private Coupon coupon;
    private LocalDate issueDate;
    private OrderCode orderCode;
    private Double freight;
    private Long sequence;

    public Order(Cpf cpf, Long sequence) {
        this(cpf, LocalDate.now(), sequence);
    }

    public Order(Cpf cpf, LocalDate issueDate, Long sequence) {
        this.cpf = cpf;
        this.items = new ArrayList<>();
        this.issueDate = issueDate;
        this.sequence = sequence;
        this.freight = 0.0;
        this.orderCode = new OrderCode(issueDate,sequence);
    }

    public void addItem(Long itemId, Double price, int quantity) {
        this.items.add(new OrderItem(itemId, price, quantity));
    }

    public void validate() throws BaseException {
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
}

