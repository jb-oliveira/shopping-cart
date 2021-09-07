package com.jb.api.domain.entity;

import com.jb.api.domain.exception.ApplicationException;
import com.jb.api.domain.exception.InvalidCouponException;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Cpf cpf;
    private List<OrderItem> items;
    private Coupon coupon;
    private Double freight;

    public Order(String cpf) {
        this.cpf = new Cpf(cpf);
        this.items = new ArrayList<>();
        this.freight = 0.0;
    }

    public void addItem(String id, Double price, int quantity) {
        this.items.add(new OrderItem(id, price, quantity));
    }

    public void validate() throws ApplicationException {
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
}

